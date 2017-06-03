<?php
	try
	{
		$connection = new PDO('mysql:host=localhost;dbname=gestiontournoi;charset=utf8', 'Tournoi', 'foot');
	}
	catch (Exception $e)
	{
		die('Erreur : ' . $e->getMessage());
	}
?>