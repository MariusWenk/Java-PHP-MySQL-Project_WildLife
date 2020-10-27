<?php
$connect = mysqli_connect("localhost", "root", "", "wildlife") or die (mysqli_error());
$sql = "SELECT * FROM spiel";
$result = mysqli_query($connect,$sql) or die(mysql_error());
echo "<table cellpadding='1' cellspacing='1' border='1'>";
echo "</tr>";
echo "<th>Spiel ID</th>";
echo "<th>Anzahl Wertungen</th>";
echo "</tr>";
while($row = mysqli_fetch_object($result))
{
echo "<tr>";
  echo "<td>" . $row->SpielID . "</td>";
  echo "<td>" . $row->Wertungen . "</td>";
}
echo "</table>";
?>