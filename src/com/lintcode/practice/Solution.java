package com.lintcode.practice;

import com.sun.deploy.util.StringUtils;

import java.util.*;

/**
 * Created by Young.Y.Yang on 2017/11/7
 */
public class Solution {

	/*
		给定两个字符串，请设计一个方法来判定其中一个字符串是否为另一个字符串的置换。
		置换的意思是，通过改变顺序可以使得两个字符串相等。
		样例:
			"abc" 为 "cba" 的置换。
			"aabc" 不是 "abcc" 的置换。
	 */
	public static boolean permutation(String A, String B) {
		// 思路1:
		/*if (A.length() != B.length()) {
			return false;
		}

		for (int i = 0; i < A.length(); i++) {
			char ch = A.charAt(i);
			int index = B.indexOf(ch);
			if (index < 0) {
				return false;
			} else {
				B = B.substring(0, index) + B.substring(index + 1);
			}
		}
		return true;*/

		// 思路2
		char[] charsA = A.toCharArray();
		char[] charsB = B.toCharArray();
		Arrays.sort(charsA);
		Arrays.sort(charsB);
		A = new String(charsA);
		B = new String(charsB);
		return A.equals(B);
	}

	/*
		给出两个字符串，你需要找到缺少的字符串
		样例:
			给一个字符串 str1 = This is an example, 给出另一个字符串 str2 = is example
			返回 ["This", "an"]
	 */
	public static List<String> missingString(String str1, String str2) {
		List<String> res = new ArrayList<>();
		if (str1.length() > str2.length()) {
			String temp = str1;
			str1 = str2;
			str2 = temp;
		}

		String[] arr1 = str1.split(" ");
		String[] arr2 = str2.split(" ");
		Set<String> set = new HashSet<>();

		for (String str : arr1) {
			set.add(str);
		}

		for (String str : arr2) {
			if (!set.contains(str)) {
				res.add(str);
			}
		}

		return res;
	}

	/*
	给 m 个数组, 每一个数组均为升序. 现在你可以从两个不同的数组中挑选两个整数(每一个数组选一个)并且计算差值.
	我们将两个整数 a 和 b 之间的差定义为它们的绝对差 |a - b|. 你的任务是去找到最大的差值.

	 注意事项

	每一个给出的数组长度至少为 1. 至少有两个不为空的数组
	m 个数组中所有整数的个数和在 [2, 10000]范围内.
	m 个数组中所有的整数均将在[-10000, 10000]范围内.
	样例
		给一个数组 [[1,2,3], [4,5], [1,2,3]], 返回 4
		获得最大差值的一种方式是在第一个数组或第三个数组中取 1, 在第二个数组中取 5.
	 */
	public static int maxDiff(int[][] arrs) {
		int res = 0;
		for (int i = 0; i < arrs.length - 1; i++) {
			for (int j = i + 1;j < arrs.length;j++) {
				int diff = Math.abs(arrs[j][arrs[j].length - 1] - arrs[i][0]);
				if (diff > res) {
					res = diff;
				}
			}
		}
		return res;
	}

	/*
		给一棵二叉搜索树以及一个整数 n, 在树中找到和为 n 的两个数字

		样例
			给一棵BST:
				4
			   / \
			  2   5
			 / \
			1   3
			以及一个整数 n = 3
			返回 [1, 2] 或 [2, 1]
	 */
	public int[] twoSum(TreeNode root, int n) {
		return null;
	}

	/*
		给一个连续的数据流,写一个函数返回终止数字到达时的第一个唯一数字（包括终止数字）,
		如果在终止数字前无唯一数字或者找不到这个终止数字, 返回 -1.
		样例
			给一个数据流 [1, 2, 2, 1, 3, 4, 4, 5, 6] 以及一个数字 5, 返回 3
			给一个数据流 [1, 2, 2, 1, 3, 4, 4, 5, 6] 以及一个数字 7, 返回 -1
	 */
	public static int firstUniqueNumber(int[] nums, int number) {
		Map<Integer, Integer> map = new LinkedHashMap <>();
		if (nums == null || nums.length == 0) {
			return -1;
		}
		boolean flag = false;
		for (int i = 0; i < nums.length; i++) {
			int n = nums[i];
			if (map.containsKey(n)) {
				int count = map.get(n);
				map.put(n, ++count);
			} else {
				map.put(n, 1);
			}
			if (nums[i] == number) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			return -1;
		}
		for (Integer key : map.keySet()) {
			if (map.get(key) == 1) {
				return key;
			}
		}
		return -1;
	}

	/*
		写一个方法, 给一个由 N 个字符构成的字符串 A和一个由 M 个字符构成的字符串 B,
		返回 A 必须重复的次数，使得 B 是重复字符串的子串.如果 B 不可能为重复字符串的子串, 则返回 -1.

		样例
			给出 A = abcd, B = cdabcdab
			你的方法需要返回 3, 因为在重复字符串 A 3次之后我们得到了字串 abcdabcdabcd. 字符串B是这个字符串的一个子串.
	 */
	public static int repeatedString(String A, String B) {
		if (A == null || A.length() == 0) {
			return -1;
		}
		int maxCount = B.length() / A.length() + 2;
		StringBuffer sb = new StringBuffer(A);
		for (int i = 1; i <= maxCount; i++) {
			if (sb.toString().contains(B)) {
				return i;
			}
			sb.append(A);
		}
		return -1;
	}

	/*
		给一个非负整数 n, 用单词打印数字
		n <= 2147483647
		样例
			给出 n = 125
			返回 one hundred twenty five
	 */
	public static String convertWords(int number) {
		if (number == 0) {
			return "zero";
		}
		String[] units = {"billion", "million", "thousand", "hundred"};
		int unitIndex = String.valueOf(number).length() % 3 == 0 ? String.valueOf(number).length() / 3 : String.valueOf(number).length() / 3 + 1;
		int billion = number / 10^9;
		int million = (number - billion * 10^9) / 10^6;
		int thousand = (number - billion * 10^9 - million * 10^6) / 10^3;
		int hundred = number - billion * 10^9 - million * 10^6 - thousand * 10^3;
		String res = "";
		if (billion > 0) {
			res += convertThree(billion) + " " + units[0] + " ";
		}
		if (million > 0) {
			res += convertThree(million) + " " + units[1] + " ";
		}
		if (thousand > 0) {
			res += convertThree(thousand) + " " + units[2] + " ";
		}
		if (hundred > 0) {
			res += convertThree(hundred);
		}

		return res.trim();
	}

	public static String convertThree(int num) {
		String[] nums = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
		String[] nums2 = {"ten", "twenty", "thirty", "forty", "fifty", "sixty", "senventy", "eighty", "ninety"};
		String[] nums3 = {"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sexteen", "seventeen", "eighteen", "nineteen"};
		String res = "";
		int bai = (num / 100) % 10;
		int shi = (num / 10) % 10;
		int ge = num % 10;

		if (bai > 0) {
			res += nums[bai - 1] + " hundred ";
		}
		if (shi == 0) {
			if (bai > 0) {
				if (ge > 0) {
					res += "and " + nums[ge - 1];
				}
			} else {
				res += nums[ge - 1];
			}
		} else if (shi == 1) {
			if (ge == 0) {
				res += nums2[0];
			} else {
				res += nums3[ge - 1];
			}
		} else {
			res += nums2[shi - 1];
			if (ge > 0) {
				res += " " + nums[ge - 1];
			}
		}
		return res;
	}

	/*
		给出一个字符串（假设长度最长为1000），求出它的最长回文子串，你可以假定只有一个满足条件的最长回文串。
		样例
			给出字符串 "abcdzdcab"，它的最长回文子串为 "cdzdc"。
	 */
	public static String longestPalindrome(String s) {

		return s;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int i = sc.nextInt();
			System.out.println(convertWords(i));
		}
	}

}
