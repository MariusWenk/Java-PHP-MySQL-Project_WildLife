<?php
$connect = mysqli_connect("localhost", "root", "", "wildlife") or die (mysqli_error());
$sql = "SELECT * FROM spiel";
$result = mysqli_query($connect,$sql) or die(mysql_error());
$ID = mysqli_fetch_array(mysqli_query($connect,"SELECT SpielID FROM spiel ORDER BY SpielID DESC LIMIT 1"))[0];
while($row = mysqli_fetch_object($result))
{
  echo $row->SpielID . "-";
}
echo "%";
$countID = $ID;
while($countID > 0){
	$ID1 = $ID - $countID + 1;
	$spieler = mysqli_fetch_array(mysqli_query($connect,"SELECT SpielerID FROM spieler WHERE SpielID = $ID1 ORDER BY SpielerID DESC LIMIT 1"))[0];
	echo $spieler. "-";
	$countID--;
}
echo "%";
$countID = $ID;
while($countID > 0){
	$ID1 = $ID - $countID + 1;
	$wertungen = mysqli_fetch_array(mysqli_query($connect,"SELECT Wertungen FROM spiel WHERE SpielID = $ID1 "))[0];
	echo $wertungen. "-";
	$countID--;
}
echo "%";
$countID = $ID;
while($countID > 0){
	$i = 1;
	$ID1 = $ID - $countID + 1;
	while($i <= 4){
		$entwicklung = mysqli_fetch_array(mysqli_query($connect,"SELECT SkillLevel FROM skill WHERE SpielID = $ID1 AND SpielerID = $i AND SkillBereich = 1"))[0]+mysqli_fetch_array(mysqli_query($connect,"SELECT SkillLevel FROM skill WHERE SpielID = $ID1 AND SpielerID = $i AND SkillBereich = 2"))[0]+mysqli_fetch_array(mysqli_query($connect,"SELECT SkillLevel FROM skill WHERE SpielID = $ID1 AND SpielerID = $i AND SkillBereich = 3"))[0]+mysqli_fetch_array(mysqli_query($connect,"SELECT SkillLevel FROM skill WHERE SpielID = $ID1 AND SpielerID = $i AND SkillBereich = 4"))[0]+mysqli_fetch_array(mysqli_query($connect,"SELECT SkillLevel FROM skill WHERE SpielID = $ID1 AND SpielerID = $i AND SkillBereich = 5"))[0]+mysqli_fetch_array(mysqli_query($connect,"SELECT SkillLevel FROM skill WHERE SpielID = $ID1 AND SpielerID = $i AND SkillBereich = 6"))[0];
		echo $entwicklung;
		if($i <= 3){
			echo ";";
		}
		$i++;
	}
	if($countID > 1){
		echo "-";
	}
	$countID--;
}
echo "%";
$countID = $ID;
while($countID > 0){
	$i = 1;
	$ID1 = $ID - $countID + 1;
	while($i <= 4){
		$plättchenGesetzt = mysqli_fetch_array(mysqli_query($connect,"SELECT COUNT(SpielerID) FROM feld WHERE SpielID = $ID1 AND SpielerID = $i"))[0];
		echo $plättchenGesetzt;
		if($i <= 3){
			echo ";";
		}
		$i++;
	}
	if($countID > 1){
		echo "-";
	}
	$countID--;
}
echo "%";
$countID = $ID;
while($countID > 0){
	$i = 1;
	$ID1 = $ID - $countID + 1;
	while($i <= 4){
		$punkte = mysqli_fetch_array(mysqli_query($connect,"SELECT Punkte FROM spieler WHERE SpielID = $ID1 AND SpielerID = $i"))[0];
		if($punkte == null){
			echo "/";
		}
		echo $punkte;
		if($i <= 3){
			echo ";";
		}
		$i++;
	}
	if($countID > 1){
		echo "-";
	}
	$countID--;
}
?>