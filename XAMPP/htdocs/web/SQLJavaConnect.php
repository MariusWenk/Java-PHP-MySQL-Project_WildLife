<?php
$connect = mysqli_connect("localhost", "root", "", "wildlife") or die (mysqli_error());

$spielID = $_GET['spielID'];

$spieler = mysqli_fetch_array(mysqli_query($connect,"SELECT SpielerID FROM spieler WHERE SpielID = $spielID ORDER BY SpielerID DESC LIMIT 1"))[0];
$spielerAmZug = mysqli_fetch_array(mysqli_query($connect,"SELECT SpielerAmZug FROM spiel WHERE SpielID = $spielID"))[0];
$wertungen = mysqli_fetch_array(mysqli_query($connect,"SELECT Wertungen FROM spiel WHERE SpielID = $spielID"))[0];

echo $spieler ."/".$spielerAmZug."/".$wertungen."/";
$spieler1 = $spieler;
$i = 1;
while($spieler1 > 0){
	$tierart = mysqli_fetch_array(mysqli_query($connect,"SELECT Tierart FROM spieler WHERE SpielID = $spielID AND spielerID = $i"))[0];
	echo $tierart;
	if($spieler1 > 1){
		echo ";";
	}
	$i++;
	$spieler1--;
}
echo "-";

$spieler1 = $spieler;
$i = 1;
while($spieler1 > 0){
	$plättchenÜbrig = mysqli_fetch_array(mysqli_query($connect,"SELECT PlaettchenUebrig FROM spieler WHERE SpielID = $spielID AND spielerID = $i"))[0];
	echo $plättchenÜbrig;
	if($spieler1 > 1){
		echo ";";
	}
	$i++;
	$spieler1--;
}
echo"-";

$n = 1;
while($n <=6){
$spieler1 = $spieler;
$i = 1;
while($spieler1 > 0){
	$skill = mysqli_fetch_array(mysqli_query($connect,"SELECT SkillLevel FROM skill WHERE SpielID = $spielID AND spielerID = $i AND SkillBereich = $n"))[0];
	echo $skill;
	if($spieler1 > 1){
		echo ";";
	}
	$i++;
	$spieler1--;
}
echo"-";
$n++;
}

$n = 1;
while($n <= 10){
$spieler1 = $spieler;
$i = 1;
while($spieler1 > 0){
	$handkarte = mysqli_fetch_array(mysqli_query($connect,"SELECT Handkarte FROM handkarten WHERE SpielID = $spielID AND spielerID = $i AND KartenNummer = $n"))[0];
	echo $handkarte;
	if($spieler1 > 1){
		echo ";";
	}
	$i++;
	$spieler1--;
}
echo"-";
$n++;
}

$spieler1 = $spieler;
$i = 1;
while($spieler1 > 0){
	$punkte = mysqli_fetch_array(mysqli_query($connect,"SELECT Punkte FROM spieler WHERE SpielID = $spielID AND spielerID = $i"))[0];
	echo $punkte;
	if($spieler1 > 1){
		echo ";";
	}
	$i++;
	$spieler1--;
}
?>