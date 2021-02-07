<?php

	require_once '../connect.php';
	$pelayan = $_GET['pelayan'];
	$stmt =mysqli_query($conn,"SELECT * FROM tb_pesanan WHERE pelayan = '$pelayan' AND status = 0");
	
	$res = 0;
	while($d = mysqli_fetch_array($stmt)){
		$res+=$d['subTotal'];
	}
	
	echo json_encode(array('success'=>1,'result'=>$res));

?>