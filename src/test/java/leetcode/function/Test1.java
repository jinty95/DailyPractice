package leetcode.function;

import cn.jinty.leetcode.function.Fun1;
import cn.jinty.leetcode.linear.ListNode;
import cn.jinty.util.ArrayUtil;
import cn.jinty.util.ListNodeUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 算法题测试
 * @Author jinty
 * @Date 2019/9/21.
 */
public class Test1 {

    private Fun1 fun = new Fun1();

    @Test
    public void testRotate(){
        int[][] matrix = {{2,29,20,26,16,28},{12,27,9,25,13,21},{32,33,32,2,28,14},{13,14,32,27,22,26},{33,1,20,7,21,7},{4,24,1,6,32,34}};
        ArrayUtil.print2DArray(matrix);
        System.out.println("---分割线---");
        fun.rotate(matrix);
        ArrayUtil.print2DArray(matrix);
    }

    @Test
    public void testFindClosest(){
        String[] words = {
                "i","am","a","good","student",".","am","i","?"
        };
        System.out.println(fun.findClosest(words,"i","student"));
        System.out.println(fun.findClosest(words,"i","a"));
    }

    @Test
    public void testSortByBits(){
        int[] arr = {4,5,6,8};
        System.out.println(Arrays.toString(arr));
        fun.sortByBits(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void testExchangeBits(){
        int num = 123;
        System.out.println(num);
        System.out.println(fun.exchangeBits(num));
    }

    @Test
    public void testOddEvenList(){
        ListNode head = ListNodeUtil.fromArray(new int[]{1,2,3,4,5});
        System.out.println(head);
        ListNode node = fun.oddEvenList(head);
        System.out.println(node);
    }

    @Test
    public void testMinSetSize(){
        int[] arr = {1,1,2,2,3,4,5,6};
        System.out.println(fun.minSetSize(arr));
    }

    @Test
    public void testHanota(){
        Long begin = System.currentTimeMillis();
        List<Integer> A = new ArrayList<>();
        List<Integer> B = new ArrayList<>();
        List<Integer> C = new ArrayList<>();
        for(int i=15;i>0;i--){
            A.add(i);
        }
        System.out.println("A列表="+A);
        System.out.println("B列表="+B);
        System.out.println("C列表="+C);
        fun.hanota(A,B,C);
        System.out.println("移动结果");
        System.out.println("A列表="+A);
        System.out.println("B列表="+B);
        System.out.println("C列表="+C);
        long end = System.currentTimeMillis();
        System.out.println("总耗时="+(end-begin)+"豪秒");
    }

    @Test
    public void testGenerateParenthesis(){
        System.out.println(fun.generateParenthesis(3));
        System.out.println(fun.generateParenthesis(2));
    }

    @Test
    public void testLexicalOrder(){
        Long begin = System.currentTimeMillis();
        System.out.println(fun.lexicalOrder(100));
        Long end = System.currentTimeMillis();
        System.out.println("执行耗时："+ (end-begin));
    }

    @Test
    public void testLongestNiceSubstring(){
        System.out.println(fun.longestNiceSubstring("AaBbCcdOiER"));
        System.out.println(fun.longestNiceSubstring("AaBBb"));
    }

    @Test
    public void testFindCircleNum(){
        int[][] isConnected = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        System.out.println(fun.findCircleNum(isConnected));
    }

    @Test
    public void testTrulyMostPopular(){
        String[] names = {"Fcclu(70)","Ommjh(63)","Dnsay(60)","Qbmk(45)","Unsb(26)","Gauuk(75)","Wzyyim(34)","Bnea(55)","Kri(71)","Qnaakk(76)","Gnplfi(68)","Hfp(97)","Qoi(70)","Ijveol(46)","Iidh(64)","Qiy(26)","Mcnef(59)","Hvueqc(91)","Obcbxb(54)","Dhe(79)","Jfq(26)","Uwjsu(41)","Wfmspz(39)","Ebov(96)","Ofl(72)","Uvkdpn(71)","Avcp(41)","Msyr(9)","Pgfpma(95)","Vbp(89)","Koaak(53)","Qyqifg(85)","Dwayf(97)","Oltadg(95)","Mwwvj(70)","Uxf(74)","Qvjp(6)","Grqrg(81)","Naf(3)","Xjjol(62)","Ibink(32)","Qxabri(41)","Ucqh(51)","Mtz(72)","Aeax(82)","Kxutz(5)","Qweye(15)","Ard(82)","Chycnm(4)","Hcvcgc(97)","Knpuq(61)","Yeekgc(11)","Ntfr(70)","Lucf(62)","Uhsg(23)","Csh(39)","Txixz(87)","Kgabb(80)","Weusps(79)","Nuq(61)","Drzsnw(87)","Xxmsn(98)","Onnev(77)","Owh(64)","Fpaf(46)","Hvia(6)","Kufa(95)","Chhmx(66)","Avmzs(39)","Okwuq(96)","Hrschk(30)","Ffwni(67)","Wpagta(25)","Npilye(14)","Axwtno(57)","Qxkjt(31)","Dwifi(51)","Kasgmw(95)","Vgxj(11)","Nsgbth(26)","Nzaz(51)","Owk(87)","Yjc(94)","Hljt(21)","Jvqg(47)","Alrksy(69)","Tlv(95)","Acohsf(86)","Qejo(60)","Gbclj(20)","Nekuam(17)","Meutux(64)","Tuvzkd(85)","Fvkhz(98)","Rngl(12)","Gbkq(77)","Uzgx(65)","Ghc(15)","Qsc(48)","Siv(47)"};
        String[] synonyms = {"(Gnplfi,Qxabri)","(Uzgx,Siv)","(Bnea,Lucf)","(Qnaakk,Msyr)","(Grqrg,Gbclj)","(Uhsg,Qejo)","(Csh,Wpagta)","(Xjjol,Lucf)","(Qoi,Obcbxb)","(Npilye,Vgxj)","(Aeax,Ghc)","(Txixz,Ffwni)","(Qweye,Qsc)","(Kri,Tuvzkd)","(Ommjh,Vbp)","(Pgfpma,Xxmsn)","(Uhsg,Csh)","(Qvjp,Kxutz)","(Qxkjt,Tlv)","(Wfmspz,Owk)","(Dwayf,Chycnm)","(Iidh,Qvjp)","(Dnsay,Rngl)","(Qweye,Tlv)","(Wzyyim,Kxutz)","(Hvueqc,Qejo)","(Tlv,Ghc)","(Hvia,Fvkhz)","(Msyr,Owk)","(Hrschk,Hljt)","(Owh,Gbclj)","(Dwifi,Uzgx)","(Iidh,Fpaf)","(Iidh,Meutux)","(Txixz,Ghc)","(Gbclj,Qsc)","(Kgabb,Tuvzkd)","(Uwjsu,Grqrg)","(Vbp,Dwayf)","(Xxmsn,Chhmx)","(Uxf,Uzgx)"};;
        System.out.println(Arrays.toString(fun.trulyMostPopular(names,synonyms)));
    }

    @Test
    public void testTotalMoney(){
        System.out.println(fun.totalMoney(10));
        System.out.println(fun.totalMoney(14));
    }

    @Test
    public void testFindContinuousSequence(){
        ArrayUtil.print2DArray(fun.findContinuousSequence(15));
        System.out.println();
        ArrayUtil.print2DArray(fun.findContinuousSequence(5));
    }

    @Test
    public void testLengthOfLongestSubstring(){
        System.out.println(fun.lengthOfLongestSubstring("abccba"));
        System.out.println(fun.lengthOfLongestSubstring("abcdefahijk"));
    }

    @Test
    public void testMaxSubArray(){
        System.out.println(fun.maxSubArray(new int[]{-1,-1,0,1}));
        System.out.println(fun.maxSubArray(new int[]{-1,-1,0,1,9,-9}));
    }

    @Test
    public void testCheckArithmeticSubarrays(){
        System.out.println(fun.checkArithmeticSubarrays(
                new int[]{5,3,7,9,1},
                new int[]{0,0,2},
                new int[]{4,3,3}
                )
        );
    }

    @Test
    public void testMergeInBetween(){
        //链表位置从0开始，第0个，第1个，以此类推
        ListNode list1 = ListNodeUtil.fromArray(new int[]{1,2,3,4,5,6});
        ListNode list2 = ListNodeUtil.fromArray(new int[]{7,8,9});
        System.out.println(fun.mergeInBetween(list1,2,3,list2));
    }

    @Test
    public void testDeckRevealedIncreasing(){
        System.out.println(
                Arrays.toString(fun.deckRevealedIncreasing(new int[]{17,13,11,2,3,5,7}))
        );
        System.out.println(
                Arrays.toString(fun.deckRevealedIncreasing(new int[]{17,13}))
        );
    }

}
