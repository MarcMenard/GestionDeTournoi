<?php 
 
$groupe_id = $_GET['groupe_id'];

 require('conn.php');
 
$sql = mysqli_query($conn,"SELECT E.nom, SUM(S.points) AS points, SUM(R.jouee) AS jouee FROM `equipe` AS E 
JOIN `groupe_equipe` AS GE ON E.id = GE.equipe_id 
JOIN `groupe` AS G ON G.id = GE.groupe_id 
JOIN `score` AS S ON S.equipe_id = E.id  
JOIN `rencontre` AS R ON R.score1_id = S.id OR R.score2_id = S.id
WHERE G.id = '".$groupe_id."'
GROUP BY S.equipe_id
ORDER BY points DESC" );

 
 $result = array();


 while ($row = mysqli_fetch_assoc($sql)){
 
 $result[] = $row;
 
 }
 
echo json_encode($result);


mysqli_close($conn);
 
?>
