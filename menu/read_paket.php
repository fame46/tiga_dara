<?php

	require_once '../connect.php';
	$stmt =mysqli_query($conn,"SELECT * FROM tb_menu WHERE jenis = 'paket'");

	$res = array();
	$i = 0;
	while($d = mysqli_fetch_array($stmt)){
		$res[$i]['id']=$d['id'];
		$res[$i]['nama_menu']=$d['nama_menu'];
		$res[$i]['harga']=$d['harga'];
		$res[$i]['jenis']=$d['jenis'];
        $res[$i]['create_on']=$d['create_on'];
        $res[$i]['create_by']=$d['create_by'];
        $res[$i]['modified_on']=$d['modified_on'];
        $res[$i]['modified_by']=$d['modified_by'];
		$res[$i]['gambar']="http://192.168.19.134/tiga_dara/image/".$d['gambar'];
		$i++;
	}

	echo json_encode(array('success'=>1,'result'=>$res));

?>