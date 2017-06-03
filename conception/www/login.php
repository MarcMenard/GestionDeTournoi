<?php
require "conn.php";
$user_name = $_POST["user_name"];
$user_pass = $_POST["password"];
$mysql_qry = "SELECT * FROM employee_data WHERE username LIKE '$user_name'  AND password LIKE '$user_pass';";
$result = mysqli_query($conn ,$mysql_qry);

if(mysqli_num_rows($result) > 0) {
	echo "connection reussie";
}
else {
	echo "dommage essaye encore";
}
?>