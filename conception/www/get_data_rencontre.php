

<?php 

$id_rencontre = $_GET['id_rencontre'];

 require('conn.php');
 
$sql = mysqli_query($conn,"SELECT E1.nom AS nom1, E2.nom AS nom2 
FROM `rencontre` AS R 
JOIN `score` AS S1 ON R.score1_id = S1.id 
JOIN `score` AS S2 ON R.score2_id = S2.id 
JOIN `equipe` AS E1 ON E1.id = S1.equipe_id 
JOIN `equipe` AS E2 ON E2.id = S2.equipe_id 
WHERE R.id = '".$id_rencontre."'" );

 
 $result = array();


 while ($row = mysqli_fetch_assoc($sql)){
 
 $result[] = $row;
 
 }
 
echo json_encode($result);


mysqli_close($conn);
 
?>
