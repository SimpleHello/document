package com.del;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 老王板砖 越办越欢
 * @author John
 *
 */
public class Xc {

	private static List<int[]> list = new ArrayList<int[]>();
	private static int[] lse ;
	public static void main(String args[]) {
		int[] a = {509,838,924,650,604,793,564,651,697,649,747,787,701,605,644};
		int limit = 5000;
		int[] axa = new int[a.length];
		System.arraycopy(a, 0, axa, 0, a.length);
		Arrays.sort(axa);
		int min = getMin(axa,a.length,limit,0);
		int max = getMax(axa,0,limit,0);
//		int min = 7;//最小 7位数==>最大的6位数相加不够5000
//		int max = 8;//最大 8位数==>最小的9位数相加超过5000
		show(a,min);
		show(a,max);
		int maxa = 0;
		for(int[] ax:list){
			int maxx = addSum(a,ax,0,ax.length);
			if(maxx<=5000&&maxx>maxa){
				maxa = maxx;
				lse = ax;
			}
			
		}
		System.out.println("最大值:"+maxa);
		showInt(lse);
	}
	
	public static void show(int[] a,int x){
		int[] sev = new int[x];
		combine_increase(a,0,sev,x,x,a.length);
	}
	// arr 为原始数组
	// start 为遍历起始位置
	// result 保存结果，为一维数组
	// count 为result数组的索引值，起辅助作用
	// num   为要选取的元素个数
	// len   为原始数组的长度，为定值
	public static void combine_increase(int[] arr, int start, int[] result, int count, int num, int len) {
		int i = 0;
		for (i = start; i < len + 1 - count; i++) {
			result[count - 1] = i;
			if (count - 1 == 0) {
				int j;
				int[] ko = new int[num];
				int sx = 0;
				for (j = num - 1; j >= 0; j--){
					ko[j] = result[j];
					sx +=arr[result[j]];
				}
				if(sx<=5000){
					list.add(ko);	
				}
					
			} else
				combine_increase(arr, i + 1, result, count - 1, num, len);
		}
	}
	
	private static void showInt(int[] ix){
		for(int i=0;i<ix.length;i++){
			System.out.print((ix[i]+1)+",");		
		}
	}
	
	public static int addSum(int[] a,int[] arr,int start,int limit){
		int sum = 0;
		for(int i = start;i<limit;i++){
			sum += a[arr[i]];
		}
		return sum;
	}
	
	public static int getMax(int[] a,int i ,int n,int max){
		int now = a[i]+max;
		if(max < n && now>n){
			return i;
		}else{
			return getMax(a,i+1,n,now);
		}
	}
	//[509, 564, 604, 605, 644, 649, 650, 651, 697, 701, 747, 787, 793, 838, 924]
	public static int getMin(int[] a,int i ,int n,int max){
		int now = a[i-1]+max;
		if(max < n && now>n){
			return a.length-i+1;
		}else{
			return getMin(a,i-1,n,now);
		}
	}
}
