<?php

# class
class Temp
{
    # function
    function alpha($a)
    {
        return $a + 2;
    }

    function beta()
    {
        echo 2 + 2;
    }

    public function __construct()
    {
        # Variable
        $v_Integer = 123;
        $v_Float = 12.34;
        $v_String = "abc";
        $v_Boolean = true;
        $v_Null = null;
        $v_Array = array("Apple", "Banana", "Orange");
        $v_Dictionary = [
            "brand" => "Ford",
            "model" => "Mustang",
            "year" => 1964
        ];
        var_dump($v_Array);
        echo $v_Array[0];
        echo $v_Dictionary["brand"];

        # If/Else
        if ($v_Integer > 122) {
            $v_Boolean = true;
        } else if ($v_Integer < 122) {
            $v_Null = 11;
        } else {
            $v_Dictionary["model"] = "Shelby";
        }

        # Loops
        while ($v_Integer > 0) {
            $v_Integer--;
            print_r($v_Integer);
            echo "<br>";
        }
        for ($i = 0; $i < count($v_Array); $i++) {
            $i = $i * 2 + 50 - 4;
            print_r($i % 2);
            print_r($i / 2);
            print_r($i ^ 2);
            echo $i;
            echo "<br>";
        }
        foreach ($v_Array as $Regex) {
            # preg_match("", "", "", "", "");
            print_r($Regex);
            echo "<br>";
        }
    }

    public function __destruct(){

    }
}

$app = new Temp();
?>