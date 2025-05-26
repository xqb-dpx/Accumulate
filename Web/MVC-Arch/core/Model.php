<?php

namespace Core;

use PDO;
use PDOException;

class Model
{
    protected static $table;
    protected static $db;

    protected static function connection()
    {
        try {
            self::$db = new PDO('mysql:host=localhost;dbname=isp_system;charset=utf8', 'root', '');
        } catch (PDOException $e) {
            die($e->getMessage());
        }
    }

    public static function select($columns = [])
    {
        if (empty($db)) {
            self::connection();
        } else {
            if ($columns) {
                $query = self::$db->query("SELECT " . implode(",", $columns) . " FROM " . static::$table);
            } else {
                $query = self::$db->query("SELECT * FROM " . self::$table);
            }
        }
        return $query->fetchAll();
    }
}