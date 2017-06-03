          <?php
	include("connection.php");
		
	if(!empty($_GET))
	{
		$username = $_GET["username"];
		$password = $_GET["password"];
		
		$request = $connection->query("SELECT * FROM `arbitre` WHERE `nom` = '$username' AND `motdepasse`= '$password'");
		$data = $request->fetch();
		
		if($data)
		{
			echo $data['id'];
		}
		else
		{
			echo "fail";
		}
	}
?>