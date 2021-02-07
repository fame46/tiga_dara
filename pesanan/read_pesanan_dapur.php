<?php

    require_once '../connect.php';
	$stmt =mysqli_query($conn,"SELECT * FROM tb_pesanan WHERE status = 2 GROUP BY atasnama , nomeja");
	$res = array();
	$i = 0;
	while($d = mysqli_fetch_array($stmt)){
        $res[$i]['atasnama']=$d['atasnama'];
        $res[$i]['nomeja']=$d['nomeja'];
		$i++;
	}
	echo json_encode(array('success'=>1,'result'=>$res));

?>