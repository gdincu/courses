package example
import mid2014._
import mid2015._
import mid2016._

import org.scalatest._

class HelloSpec extends FlatSpec with Matchers {
  "Mid2016 scanLeft" should "return correct results" in {
    Mid2016.scanLeft(List("A", "B", "C"))("z")(_ + _) shouldEqual List("zA", "zAB", "zABC")
    Mid2016.scanLeft(List("A"))("z")(_ + _) shouldEqual List("zA")
    Mid2016.scanLeft(List())("z")(_ + _) shouldEqual List()
  }

  "Mid2016 flatMap" should "return correct results" in {
    Mid2016.flatMap(List("Ana", "are", "mere"))((x: String) => x.toList) shouldEqual "Anaaremere".toList
  }

  "Reachable should be correctly implemented" should "return correct results" in {
    Mid2016.reachable(1, Set(Node(1)),List(Edge(Node(1), Node(2)))) shouldEqual Set(Node(2))
    Mid2016.reachable(2, Set(Node(1)),List(Edge(Node(1), Node(2)), Edge(Node(2), Node(1)))) shouldEqual Set(Node(1))
  }

  "Cycle 3 should detect 3 cycles" should "return correct results" in {
    Mid2016.cycles3(Set(Node(1)), List(Edge(Node(1), Node(2)))) shouldEqual Set()
    Mid2016.cycles3(Set(Node(1)), List(Edge(Node(1), Node(2)), Edge(Node(2), Node(3)), Edge(Node(3), Node(1)))) shouldEqual Set(Node(1))
    Mid2016.cycles3(Set(Node(1), Node(2), Node(3)), List(Edge(Node(1), Node(2)), Edge(Node(2), Node(3)), Edge(Node(3), Node(1)))) shouldEqual Set(Node(1), Node(2), Node(3))
  }

  "Difference on nil" should "be nil" in {
    Mid2015.differences(Nil) shouldEqual Nil
  }

  "Difference on one-element list" should "be that element" in {
    Mid2015.differences(List(1)) shouldEqual List(1)
  }

  "Difference on general list" should "be correct" in {
    Mid2015.differences(List(1, 2)) shouldEqual List(1, 1)
  }

  "difference on longer test" should "be correct" in {
    Mid2015.differences(List(1, -2, 3, -4, 5, -6)) shouldEqual List(1, -3, 5, -7, 9, -11)
  }

  "rebList1" should "work" in {
    Mid2015.rebuildList(Nil) shouldEqual Nil
  }

  "rebList2" should "work" in {
    Mid2015.rebuildList(List(1)) shouldEqual List(1)
  }

  "rebList3" should "work" in {
    Mid2015.rebuildList(Mid2015.differences(List(1, -2, 3, -4, 5, -6))) shouldEqual List(1, -2, 3, -4, 5, -6)
  }

  "minMax One element" should "work" in {
    Mid2015.computeMinMax(TNode(Leaf, 1, Leaf)) shouldEqual (1, 1)
  }

  "minMax Two on left element" should "work" in {
    Mid2015.computeMinMax(TNode(TNode(Leaf, 10, Leaf), 1, Leaf)) shouldEqual (1, 10)
  }

  "minMax Two on left and right element" should "work" in {
    Mid2015.computeMinMax(TNode(TNode(Leaf, 10, Leaf), 1, TNode(Leaf, -10, Leaf))) shouldEqual (-10, 10)
  }

  "isBST" should "work" in {
    Mid2015.isBinarySearchTree(TNode(TNode(Leaf, -10, Leaf), 1, TNode(Leaf, 10, Leaf))) shouldEqual true
  }

  "isBST false" should "work" in {
    Mid2015.isBinarySearchTree(TNode(TNode(Leaf, 10, Leaf), 1, TNode(Leaf, 10, Leaf))) shouldEqual false
  }

  "Merge two lists" should "work" in {
    (Mid2014.merge(List(1, 4, 7, 10, 11), List(2, 3, 5, 7, 12, 16))((x, y) => x <= y)) shouldEqual List(1, 2, 3, 4, 5, 7, 7, 10, 11, 12, 16)
  }
  "Merge two lists tail rec" should "work" in {
    (Mid2014.merge2(List(1, 4, 7, 10, 11), List(2, 3, 5, 7, 12, 16))((x, y) => x <= y)) shouldEqual List(1, 2, 3, 4, 5, 7, 7, 10, 11, 12, 16)
  }
  "Stream in natural numbers first 3 numbers" should "work" in {
    Mid2014.iterate(1)(_ + 1).take(3).toList shouldEqual List(1, 2, 3)
  }
  "Stream power of two" should "work" in {
    Mid2014.iterate(1)(_ * 2).take(4).toList shouldEqual List(1, 2, 4, 8)
  }
  "Flatten a list" should "work" in {
    val ls: List[Any] = List(5, List(84, 12), List(3, List(-4, 7)))
    Mid2014.flatten(ls) shouldEqual List(5, 84, 12, 3, -4, 7)
  }
}