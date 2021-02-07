<?php
	require_once '../connect.php';
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		$response = array();
		$id = $_POST['id'];
		$sql = "DELETE FROM tb_meja WHERE id = '$id'";
		if (mysqli_query($conn,$sql)){
			$response['value'] = 1;
			$response['message'] = "Delete Successfully";
			echo json_encode($response);
		}else{
			$response['value'] = 0;
			$response['message'] = "Delete Failed";
			echo json_encode($response);
        }
		mysqli_close($conn);
	}
?>
