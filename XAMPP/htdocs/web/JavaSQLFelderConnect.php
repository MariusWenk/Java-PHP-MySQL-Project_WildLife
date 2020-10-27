<?php
$connect = mysqli_connect("localhost", "root", "", "wildlife") or die (mysqli_error());
$newID = mysqli_fetch_array(mysqli_query($connect,"SELECT SpielID FROM spiel ORDER BY SpielID DESC LIMIT 1"))[0];
$data = $_GET['data'];
$dataLength = $_GET['dataLength'];

$splitter1 = '[-]';
$splitter2 = '[;]';
$split = preg_split ( $splitter1 , $data );
$n = 0;
while($n < $dataLength){
$split[$n] = preg_split ( $splitter2 , $split[$n] );
$n++;
}

$i = 0;
while($i < $dataLength){
$tierart = $split[$i][2];
$spielerID = mysqli_fetch_array(mysqli_query($connect,"SELECT SpielerID FROM spieler WHERE SpielID = $newID AND Tierart = $tierart"))[0]; 
$xPos = $split[$i][0];
$yPos = $split[$i][1];
$feldID = $i + 1;
echo $feldID." ".$newID." ".$spielerID." ".$xPos." ".$yPos;
mysqli_query($connect, "INSERT INTO feld VALUES($feldID,$newID,$spielerID,$xPos,$yPos)");
$i++;
}

?>