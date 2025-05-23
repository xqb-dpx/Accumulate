<?php
// MVC ---> Model, View, Controller
class db
{
    protected $_Connection;

    public function __construct($host, $dbname, $charset = 'utf8')
    {
        try {
            $this->_Connection = new PDO("mysql:host=$host;dbname=$dbname;charset=$charset;", "root", "");
        } catch (PDOException $e) {
            echo $e->getMessage();
        }
    }

    public function Insert($table, $columns, $data)
    {
        // for loop
        $q = [];
        for ($i = 0; $i < count($columns); $i++) {
            $q[] = "?";
        }
        $query = $this->_Connection->prepare("INSERT INTO $table (" . implode(", ", $columns) . ") VALUES (" . implode(", ", $q) . ")");
        return $query->execute($data);
    }

//    public function Update($table, $columns, $value, $condition)
//    {
//    }
//
//    public function Delete($table, $where)
//    {
//    }
//
//    public function Select($table, $where)
//    {
//    }

    public function __destruct()
    {
        $this->_Connection = null;
    }
}

$DB = new db("localhost", "shop");
$c = ["title", "summary", "details", "time", "image"];
$d = ["Title", "Summary", "Details", time(), "image"];
if ($DB->Insert("products", $c, $d)) {
    echo "Data inserted successfully";
} else {
    echo "Data insertion failed";
}