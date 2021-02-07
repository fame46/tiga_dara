<?php

    require_once '../connect.php';
    $create_on = $_GET['create_on'];
    // $nomeja = $_GET['nomeja'];


	$stmt =mysqli_query($conn,"SELECT * FROM tb_pesanan WHERE DATE_FORMAT(create_on, '%d-%m-%Y') = '22-01-2021' AND atasnama != 'NULL' ORDER BY id ASC");
	$res = array();
	$i = 0;
	while($d = mysqli_fetch_array($stmt)){
		$res[$i]['id']=$d['id'];
		$res[$i]['notrans']=$d['notrans'];
	}
	echo json_encode(array('success'=>1,'result'=>$res));

?>