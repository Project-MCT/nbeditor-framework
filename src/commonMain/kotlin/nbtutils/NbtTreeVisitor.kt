package nbtutils

import platform.posix.wcswidth

interface NbtTreeVisitor {
  fun visit(tree: NbtTreeNode<Any>){
    when (tree) {
      is NbtTreeNode.NbtCombination -> visitCombination(tree)
      is NbtTreeNode.NbtArray -> visitArray(tree)
      is NbtTreeNode.NbtLabel -> visitLabel(tree)
      is NbtTreeNode.NbtByte -> visitByte(tree)
      is NbtTreeNode.NbtShort -> visitShort(tree)
      is NbtTreeNode.NbtInt -> visitInt(tree)
      is NbtTreeNode.NbtLong -> visitLong(tree)
      is NbtTreeNode.NbtFloat -> visitFloat(tree)
      is NbtTreeNode.NbtDouble -> visitDouble(tree)
      is NbtTreeNode.NbtBoolean -> visitBoolean(tree)

      else -> throw IllegalArgumentException("unknown node type \"${tree::class}\"")
    }
  }

  fun visitCombination(comb: NbtTreeNode.NbtCombination){
    for (entry in comb) {
      visitAttribute(entry.key, entry.value)
    }
  }

  fun visitAttribute(name: String, value: NbtTreeNode<Any>){
    visit(value)
  }

  fun visitArray(arr: NbtTreeNode.NbtArray){
    for (node in arr) {
      visit(node)
    }
  }

  fun visitLabel(label: NbtTreeNode.NbtLabel){}
  fun visitByte(byte: NbtTreeNode.NbtByte){}
  fun visitShort(short: NbtTreeNode.NbtShort){}
  fun visitInt(int: NbtTreeNode.NbtInt){}
  fun visitLong(long: NbtTreeNode.NbtLong){}
  fun visitFloat(float: NbtTreeNode.NbtFloat){}
  fun visitDouble(double: NbtTreeNode.NbtDouble){}
  fun visitBoolean(bool: NbtTreeNode.NbtBoolean){}
}