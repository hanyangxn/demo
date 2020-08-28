package com.hy.demo.test;

import java.util.Scanner;

/**
 * @author hy
 * @description:
 * @date 2020/08/28
 */
public class TreeTest {
        private TreeTest left,right;
        private char data;
        public TreeTest creat(String des){
            Scanner scanner=new Scanner(System.in);
            System.out.println("des:"+des);

            String str=scanner.next();
            if(str.charAt(0)<'a')
                return null;
            TreeTest root=new TreeTest();
            root.data=str.charAt(0);
            root.left=creat(str.charAt(0)+"左子树");
            root.right=creat(str.charAt(0)+"右子树");
            return root;
        }
        public void midprint(TreeTest btree){
//        中序遍历
            if(btree!=null){
                midprint(btree.left);
                System.out.print(btree.data+"  ");
                midprint(btree.right);
            }
        }
        public void firprint(TreeTest btree){
//        先序遍历
            if(btree!=null){
                System.out.print(btree.data+" ");
                firprint(btree.left);
                firprint(btree.right);
            }
        }
        public void lastprint(TreeTest btree){
//        后序遍历
            if(btree!=null){
                lastprint(btree.left);
                lastprint(btree.right);
                System.out.print(btree.data+"  ");
            }
        }
        public static void main(String[] args) {
            TreeTest tree = new TreeTest();
            TreeTest newtree=tree.creat("根节点");
            tree.firprint(newtree);
            System.out.println();
            tree.midprint(newtree);
            System.out.println();
            tree.lastprint(newtree);
    }
}
