<?php
    include("connection.php");
            
    if(!empty($_GET))
    {
        $nombre_buts_1 = $_GET["nombre_buts_1"];
		$nombre_buts_2 = $_GET["nombre_buts_2"];
        $points_1 = $_GET["points_1"];
        $points_2 = $_GET["points_2"];
        $forfait = $_GET["forfait"];
		$victoire_1 = $_GET["victoire_1"];
		$victoire_2 = $_GET["victoire_2"];
		$defaite_1 = $_GET["defaite_1"];
		$defaite_2 = $_GET["defaite_2"];
		$nul = $_GET["nul"];
		$id = $_GET["id"];
		
        /*$request = $connection->query("SELECT * FROM `arbitre` WHERE `nom` = '$username' AND `motdepasse`= '$password'");
        $data = $request->fetch();
        
        if($data)
        {*/
	   $connection->exec("UPDATE rencontre AS R
		JOIN `score` AS S1 ON  R.score1_id = S1.id
		JOIN `score` AS S2 ON  R.score2_id = S2.id
		SET R.jouee = 1, 
		S1.nombre_buts = '".$nombre_buts_1."', S1.points = '".$points_1."',S1.victoire = '".$victoire_1."',S1.defaite = '".$defaite_1."',
		S1.nul = '".$nul."',S1.forfait = '".$forfait."', 
		S2.nombre_buts = '".$nombre_buts_2."', S2.points = '".$points_2."',S2.victoire = '".$victoire_2."',S2.defaite = '".$defaite_2."',
		S2.nul = '".$nul."',S2.forfait = '".$forfait."'  
		WHERE R.id = '".$id."'");
		
		echo "Donnée envoyée !";
			/*
		   echo "Success";
        }
        else
        {
            echo "fail";
        }*/
    }
?>