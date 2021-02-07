<?php

	require_once '../connect.php';
	$atasnama = $_GET['atasnama'];
    $nomeja = $_GET['nomeja'];
	$stmt =mysqli_query($conn,"SELECT * FROM tb_pesanan WHERE  atasnama = '$atasnama' AND nomeja = '$nomeja' AND status = 1");
	
	$res = 0;
	while($d = mysqli_fetch_array($stmt)){
		$res+=$d['subTotal'];
	}
	
	echo json_encode(array('success'=>1,'result'=>$res));

?>