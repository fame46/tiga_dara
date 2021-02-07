<?php

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$username = $_POST['username'];
	$password = md5($_POST['password']);

	require_once '../connect.php';

	$sql = "SELECT * FROM tb_user WHERE username = '$username' AND password = '$password'";

	$response = mysqli_query($conn, $sql);

	$result = array();

	if(mysqli_num_rows($response) == 1){

		$row = mysqli_fetch_assoc ($response);

		if($row>0){

			$result['success'] = "1";
            $result['message'] = "success";
            $result['username'] = $row['username'];
			$result['level'] = $row['level'];
			echo json_encode($result);

			mysqli_close($conn);

		}else {
			$result['success'] = "0";
			$result['message'] = "error";
			echo json_encode($result);

			mysqli_close($conn);
		}
	}else {
			$result['success'] = "0";
			$result['message'] = "error";
			echo json_encode($result);

			mysqli_close($conn);
		}
}

?>