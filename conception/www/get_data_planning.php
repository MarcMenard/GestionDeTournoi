<?php 
 
 require('conn.php');
 
$sql = mysqli_query($conn,"SELECT E1.nom AS nom1, S1.nombre_buts AS nbbuts1, S2.nombre_buts AS nbbuts2, E2.nom AS nom2, R.heure, R.terrain
FROM `rencontre` AS R 
JOIN `score` AS S1 ON  R.score1_id = S1.id
JOIN `score` AS S2 ON  R.score2_id = S2.id
JOIN `equipe` AS E1 ON E1.id = S1.equipe_id
JOIN `equipe` AS E2 ON E2.id = S2.equipe_id" );
 
 $result = array();
 
 while ($row = mysqli_fetch_assoc($sql)){
 
 $result[] = $row;

 }
 
echo json_encode($result);

mysqli_close($conn);
 
?>  