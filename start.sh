#!/bin/sh
PROBLEM=$1
PROBLEM=`echo $PROBLEM | sed 's,/$,,'`
YEAR=`echo $PROBLEM | awk -F- '{print $1}'`
DAY=`echo $PROBLEM | sed 's,.*day,,'`
URL=https://adventofcode.com/$YEAR/day/$DAY
mkdir -p $PROBLEM
if [ -f $PROBLEM ]; then
  exit 1
fi
if [ ! -f "$PROBLEM/README.md" ]; then
  curl -s $URL > $PROBLEM/README.md
  echo >> $PROBLEM/README.md
  echo "<a href=\"$URL\">$URL</a>" >> $PROBLEM/README.md
fi

# Java
if [ ! -f "$PROBLEM/Solution.java" ]; then
  cat <<END > $PROBLEM/Solution.java
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    Queue<String> readFile(String pathname) {
        Queue<String> queue = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(new File(pathname));
            while (scanner.hasNextLine())
                queue.add(scanner.nextLine());
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queue;
    }

    public void part1() {
        Queue<String> file = readFile();
        while (!file.isEmpty()) {
            String line = file.remove();
        }
    }

    public void part2() {
        Queue<String> file = readFile();
        while (!file.isEmpty()) {
            String line = file.remove();
        }
    }

    @Test
    public void small1() {
        Assert.assertEquals("", part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals("", part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals("", part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals("", part2("large"));
    }
}
END
fi

# Python3
if [ ! -f "$PROBLEM/Solution.py" ]; then
  cat <<END > $PROBLEM/Solution.py
import unittest
from typing import *


class Solution(unittest.TestCase):

    def part1(self):
        self.assertEqual(1, 1)

    def part2(self):
        self.assertEqual(2, 2)
END
fi

# Golang
if [ ! -f "$PROBLEM/Solution.go" ]; then
  cd $PROBLEM
  go mod init $PROBLEM
  cd ..
  cat <<END > $PROBLEM/Solution.go
package main
END
  cat <<END > $PROBLEM/Solution_test.go
package main

import (
    "github.com/stretchr/testify/assert"
    "testing"
)

func Test1(t *testing.T) {
    assert.Equal(t, 1, 1)
}

func Test2(t *testing.T) {
    assert.Equal(t, 2, 2)
}

func Test3(t *testing.T) {
    assert.Equal(t, 3, 3)
}
END
  go mod tidy $PROBLEM/Solution.go $PROBLEM/Solution_test.go
fi

# Ruby
if [ ! -f "$PROBLEM/Solution.rb" ]; then
  touch $PROBLEM/Solution.rb
  cat <<END > $PROBLEM/Solution_test.rb
require 'test/unit'
require_relative 'Solution'

class TestSolution < Test::Unit::TestCase
  def part1
    assert_equal(1, 1)
  end

  def part2
    assert_equal(2, 2)
  end
end
END
fi

# PHP
if [ ! -f "$PROBLEM/Solution.php" ]; then
  cat <<END > $PROBLEM/Solution.php
<?php

class Solution
{
}
END
  cat <<END > $PROBLEM/SolutionTest.php
<?php

use PHPUnit\Framework\TestCase;

require_once 'Solution.php';

class SolutionTest extends TestCase
{

    function part1()
    {
        \$this->assertEquals(1, 1);
    }

    function part2()
    {
        \$this->assertEquals(2, 2);
    }
}
END
fi

idea -w $PROBLEM
#pycharm $PROBLEM
#rubymine $PROBLEM
#goland $PROBLEM
make
