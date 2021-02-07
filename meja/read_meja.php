<?php

	require_once '../connect.php';
	$stmt =mysqli_query($conn,"SELECT * FROM tb_meja");
	$res = array();
	$i = 0;
	while($d = mysqli_fetch_array($stmt)){
		$res[$i]['id']=$d['id'];
        $res[$i]['meja']=$d['meja'];
        $res[$i]['created_on']=$d['created_on'];
        $res[$i]['created_by']=$d['created_by'];
        $res[$i]['modified_on']=$d['modified_on'];
        $res[$i]['modified_by']=$d['modified_by'];
		$i++;
	}
	echo json_encode(array('success'=>1,'result'=>$res));

?>