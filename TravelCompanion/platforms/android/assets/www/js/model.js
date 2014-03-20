var db;
var dataset;

	function initDatabase() {
    console.debug('called initDatabase()');

    try {
        if (!window.openDatabase) {
            alert('not supported');
        } else {
            var shortName = 'HotelDatabase';
            var version = '1.0';
            var displayName = 'My Test Database Example';
            var maxSizeInBytes = 65536;
            db = openDatabase(shortName, version, displayName, maxSizeInBytes);

            createTableIfNotExists();
        }
    } catch(e) {
        if (e == 2) {
            alert('Invalid database version');
        } else {
            alert('Unknown error ' + e);
        }
        return;
    }
	}

	function createTableIfNotExists() {
    console.debug('called createTableIfNotExists()');

    var sql = "CREATE TABLE IF NOT EXISTS Contacts (id INTEGER PRIMARY KEY AUTOINCREMENT, roomNo TEXT, reserveTime TEXT, name TEXT, phone TEXT, email TEXT)";

    db.transaction(
        function (transaction) {
            transaction.executeSql(sql, [], showRecords, handleErrors);
            console.debug('executeSql: ' + sql);
        }
    );
	}

	function insertRecord() {
    console.debug('called insertRecord()');
	
	var roomNo = $('#roomNo').val();
	var reserveTime = $('#reserveTime').val();
    var name = $('#name').val();
    var phone = $('#phone').val();
	var email = $('#email').val();

    var sql = 'INSERT INTO Contacts (roomNo, reserveTime, name, phone, email) VALUES (?, ?, ?, ?, ?)';

    db.transaction(
        function (transaction) {
            transaction.executeSql(sql, [roomNo, reserveTime, name, phone, email], showRecordsAndResetForm, handleErrors);
            //transaction.executeSql(sql, [name, phone], showRecords, handleErrors);
            console.debug('executeSql: ' + sql);
			alert('Added Reservation!');
        }
    );
	}

	function deleteRecord(id) {
    console.debug('called deleteRecord()');

    var sql = 'DELETE FROM Contacts WHERE id=?';

    db.transaction(
        function (transaction) {
            transaction.executeSql(sql, [id], showRecords, handleErrors);
            console.debug('executeSql: ' + sql);
            alert('Delete Sucessfully');
        }
    );

    resetForm();
	}

	function updateRecord() {
    console.debug('called updateRecord()');
	
	var roomNo = $('#roomNo').val();
	var reserveTime = $('#reserveTime').val();
    var name = $('#name').val();
    var phone = $('#phone').val();
	var email = $('#email').val();
    var id = $("#id").val();

    var sql = 'UPDATE Contacts SET roomNo=?, reserveTime=?, name=?, phone=?, email=? WHERE id=?';

    db.transaction(
        function (transaction) {
            transaction.executeSql(sql, [roomNo, reserveTime, name, phone, email, id], showRecordsAndResetForm, handleErrors);
            console.debug('executeSql: ' + sql);
        }
    );
	}

	function dropTable() {
    console.debug('called dropTable()');

    var sql = 'DROP TABLE Contacts';

    db.transaction(
        function (transaction) {
            transaction.executeSql(sql, [], showRecords, handleErrors);
        }
    );

    resetForm();

    initDatabase();
	}

	function loadRecord(i) {
    console.debug('called loadRecord()');

    var item = dataset.item(i);
	
	$('#roomNo').val(item['roomNo']);
	$('#reserveTime').val(item['reserveTime']);
    $('#name').val(item['name']);
    $('#phone').val(item['phone']);
	$('#email').val(item['email']);
    $('#id').val(item['id']);
	}

	function resetForm() {
    console.debug('called resetForm()');
	
	$('#roomNo').val('');
	$('#reserveTime').val('');
    $('#name').val('');
    $('#phone').val('');
	$('#email').val('');
    $('#id').val('');
	}

	function showRecordsAndResetForm() {
    console.debug('called showRecordsAndResetForm()');

    resetForm();
    showRecords()
	}

	function handleErrors(transaction, error) {
    console.debug('called handleErrors()');
    console.error('error ' + error.message);

    alert(error.message);
    return true;
	}

	function showRecords() {
    console.debug('called showRecords()');

    var sql = "SELECT * FROM Contacts";

    db.transaction(
        function (transaction) {
            transaction.executeSql(sql, [], renderRecords, handleErrors);
        }
    );
	}

	function renderRecords(transaction, results) {
    console.debug('called renderRecords()');

    html = '';
    $('#results').html('');

    dataset = results.rows;

    if (dataset.length > 0) {
        html = html + '<br/><br/>';

        html = html + '<table class="table table-bordered">';
        html = html + '  <caption></caption>';
        html = html + '  <thead>';
        html = html + '    <tr>';
        html = html + '      <th class="span1">Reservation</td>';
		html = html + '      <th>Room</td>';
		html = html + '      <th>Time</td>';
        html = html + '      <th>Name</td>';
        html = html + '      <th>Contact</td>';
        html = html + '      <th class="span1"></td>';
        html = html + '    </tr>';
        html = html + '  </thead>';

        html = html + '  <tbody>';

        for (var i = 0, item = null; i < dataset.length; i++) {
            item = dataset.item(i);

            html = html + '    <tr>';
            html = html + '      <td>' + item['id'] + '</td>';
			html = html + '      <td>' + item['roomNo'] + '</td>';
			html = html + '      <td>' + item['reserveTime'] + '</td>';
            html = html + '      <td>' + item['name'] + '</td>';
            html = html + '      <td>' + item['phone'] + '</td>';
            html = html + '      <td>';
            html = html + '        <button type="button" class="btn btn-primary btn-mini" onClick="loadRecord(' + i + ');">edit</button>';
            html = html + '        <button type"button" class="btn btn-danger btn-mini" onClick="deleteRecord(' + item['id'] + ');">delete</button>';
            html = html + '      </td>';
            html = html + '    </tr>';
        }

        html = html + '  </tbody>';
        html = html + '</table>';

        $('#results').append(html);
    }
	}

	function updateCacheContent(event) {
    console.debug('called updateCacheContent()');
    window.applicationCache.swapCache();
	}

	$(document).ready(function () {
    window.applicationCache.addEventListener('updateready', updateCacheContent, false);

    initDatabase();
});