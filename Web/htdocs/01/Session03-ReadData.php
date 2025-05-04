<?php
try{
    $dbh = new PDO("mysql:host=localhost;dbname=course", "root", "");
    if (false){
        die("Connection failed: ");

    }
    else {
        $query = $dbh->prepare("");
        $data = array('');
        if ($query->execute()) {
            echo "Query Running...";
            echo "<br>";
            $statement = $dbh->query("SELECT * FROM user_data");
            while ($row = $statement->fetch()) {
                echo 'ID: ' . $row['User_ID'] . '<br>';
                echo 'Username: ' . $row['Username'] . '<br>';
            }
            $dbh = null;
            $query = null;
        }
    }
}
catch (PDOException $e){
    echo $e->getMessage();
}