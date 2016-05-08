<?php
    $con = mysqli_connect("mysql6.000webhost.com", "a6472237_user", "android123", "a6472237_data");
    
    $A1 = $_POST["A1"];
    $A2 = $_POST["A2"];
    $A3 = $_POST["A3"];
    $B1 = $_POST["B1"];
    $B2 = $_POST["B2"];
    $B3 = $_POST["B3"];
    $C1 = $_POST["C1"];
    $C2 = $_POST["C2"];
    $C3 = $_POST["C3"];
    
    $statement = mysqli_prepare($con, "INSERT INTO tictactoe WHERE A1 = ? AND A2 = ? AND A3 = ? AND B1 = ? AND B2 = ? AND B3 = ? AND C1 = ? AND C2 = ? AND C3 = ?");
    mysqli_stmt_bind_param($statement, "sssssssss", $A1, $A2, $A3, $B1, $B2, $B3, $C1, $C2, $C3);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement,  $A1, $A2, $A3, $B1, $B2, $B3, $C1, $C2, $C3);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>