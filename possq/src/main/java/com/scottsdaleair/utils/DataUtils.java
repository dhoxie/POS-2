package com.scottsdaleair.utils;

public class DataUtils {

  /**
   * Add an item to a generic array.
   * 
   * @param <T>  The type of the array
   * @param item An item of type T to add
   * @param arr  Array of type T to add to
   * @return Original array with {@code item} at the end
   */
  public static <T> T[] addToArray(T item, T[] arr) {
    @SuppressWarnings("unchecked")
    T[] newArr = (T[]) new Object[arr.length + 1];
    for (int i = 0; i < arr.length; i++) {
      newArr[i] = arr[i];
    }
    newArr[arr.length] = item;
    return newArr;
  }

  /**
   * Remove the given index from an array.
   * 
   * @param <T>   The type of the array
   * @param index The index of the item to be removed
   * @param arr   The array to remove from
   * @return The passed in array with the item at {@code index} removed. If the
   *         index is out of bounds, the last item will be removed.
   */
  public static <T> T[] removeFromArray(int index, T[] arr) {
    @SuppressWarnings("unchecked")
    T[] newArr = (T[]) new Object[arr.length - 1];
    for (int i = 0, j = 0; i < newArr.length; i++, j++) {
      if (j == index) {
        j++;
      }
      newArr[i] = arr[j];
    }
    return newArr;
  }
}