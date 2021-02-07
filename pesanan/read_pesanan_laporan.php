<?php

    require_once '../connect.php';
    $awal = $_GET['awal'];
    $akhir = $_GET['akhir'];
	$stmt =mysqli_query($conn,"SELECT * FROM tb_pesanan WHERE status = 4 AND DATE_FORMAT(create_on, '%d-%m-%Y') >= '$awal'
                                AND DATE_FORMAT(create_on, '%d-%m-%Y') <= '$akhir' GROUP BY atasnama , nomeja");
	$res = array();
	$i = 0;
	while($d = mysqli_fetch_array($stmt)){
        $res[$i]['atasnama']=$d['atasnama'];
        $res[$i]['nomeja']=$d['nomeja'];
        $res[$i]['kasir']=$d['kasir'];
        $res[$i]['create_on']=date('d-m-Y',strtotime($d['create_on']));
		$i++;
	}
	echo json_encode(array('success'=>1,'result'=>$res));

?>