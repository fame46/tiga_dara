<?php

    require_once '../connect.php';
    $atasnama = $_GET['atasnama'];
    $nomeja = $_GET['nomeja'];
	$stmt =mysqli_query($conn,"SELECT * FROM tb_pesanan WHERE atasnama = '$atasnama' AND nomeja = '$nomeja' AND status = 3");
	$res = array();
	$i = 0;
	while($d = mysqli_fetch_array($stmt)){
		$res[$i]['id']=$d['id'];
        $res[$i]['nama_menu']=$d['nama_menu'];
        $res[$i]['harga_menu']=$d['harga_menu'];
        $res[$i]['subTotal']=$d['subTotal'];
        $res[$i]['jumlah']=$d['jumlah'];
        $res[$i]['keterangan']=$d['keterangan'];
        $res[$i]['atasnama']=$d['atasnama'];
        $res[$i]['status']=$d['status'];
        $res[$i]['nomeja']=$d['nomeja'];
        $res[$i]['pelayan']=$d['pelayan'];
        $res[$i]['kasir']=$d['kasir'];
        $res[$i]['dapur']=$d['dapur'];
        $res[$i]['create_on']=$d['create_on'];
        $res[$i]['create_by']=$d['create_by'];
        $res[$i]['modified_on']=$d['modified_on'];
        $res[$i]['modified_by']=$d['modified_by'];
		$i++;
	}
	echo json_encode(array('success'=>1,'result'=>$res));

?>