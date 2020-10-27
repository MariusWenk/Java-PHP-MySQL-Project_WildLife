<?php
$connect = mysqli_connect("localhost", "root", "", "wildlife") or die (mysqli_error());

$spielID = $_GET['spielID'];

$belegteFelder = mysqli_fetch_array(mysqli_query($connect,"SELECT FeldID FROM feld WHERE SpielID = $spielID ORDER BY FeldID DESC LIMIT 1"))[0];

$i = 1;
while($belegteFelder > 0){
	$xPos = mysqli_fetch_array(mysqli_query($connect,"SELECT xPosition FROM feld WHERE SpielID = $spielID AND FeldID = $i"))[0];
	$yPos = mysqli_fetch_array(mysqli_query($connect,"SELECT yPosition FROM feld WHERE SpielID = $spielID AND FeldID = $i"))[0];
	$spieler = mysqli_fetch_array(mysqli_query($connect,"SELECT SpielerID FROM feld WHERE SpielID = $spielID AND FeldID = $i"))[0];
	$tierart = mysqli_fetch_array(mysqli_query($connect,"SELECT Tierart FROM spieler WHERE SpielID = $spielID AND SpielerID = $spieler"))[0];
	echo $xPos.";".$yPos.";".$tierart.";".$spieler;
	if($belegteFelder > 1){
		echo "-";
	}
	$belegteFelder--;
	$i++;
}
?>