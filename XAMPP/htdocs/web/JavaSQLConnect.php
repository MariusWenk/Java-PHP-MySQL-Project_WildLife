<?php
$connect = mysqli_connect("localhost", "root", "", "wildlife") or die (mysqli_error());
$newID = mysqli_fetch_array(mysqli_query($connect,"SELECT SpielID FROM spiel ORDER BY SpielID DESC LIMIT 1"))[0] + 1;
$spieler = $_GET['spieler'];
$spielerAmZug = $_GET['spielerAmZug'];
$data = $_GET['data'];
$wertungen = $_GET['wertungen'];

mysqli_query($connect,"INSERT INTO spiel VALUES ($newID, $wertungen, $spielerAmZug)");

$splitter1 = '[-]';
$splitter2 = '[;]';
$split = preg_split ( $splitter1 , $data );
$n = 0;
while($n < 19){
$split[$n] = preg_split ( $splitter2 , $split[$n] );
$n++;
}

while($spieler > 0){
$spieler1 = $spieler - 1;
$tierart = $split[0][$spieler1];
$punkte = $split[18][$spieler1];
$plättchenÜbrig = $split[1][$spieler1];
mysqli_query($connect,"INSERT INTO spieler VALUES ($spieler,$newID,$tierart,$punkte,$plättchenÜbrig)");
$i = 1;
while($i <= 10){
$t = 7 + $i;
$handkarte = $split[$t][$spieler1];
mysqli_query($connect,"INSERT INTO handkarten VALUES ($spieler,$newID,$i,$handkarte)");
$i++;
}
$z = 1;
while($z <= 6){
$o = 1 + $z;
$skill = $split[$o][$spieler1];
mysqli_query($connect,"INSERT INTO skill VALUES ($spieler,$newID,$z,$skill)");
$z++;
}
$spieler--;
}
?>