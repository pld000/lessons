////////////////////////////////// 1 //////////////////////////////////
/**
 * Информативный комментарий
 * */

// Паттерн валидации цвета в формате #XXXXXX
export const COLOR_PATTERN = '^#[0-9A-Fa-f]{6}$';

////////////////////////////////// 2 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 3 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 4 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 5 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 6 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 7 //////////////////////////////////
/**
 * Предупреждение
 * */

// Устаревший метод, для обратной совместимости, в новых версиях испльзуйте getCategoriesForBakery
function getCategories(bakeryId: number, locale?: string): Promise<FilterCategory[]> {
  return this._productApi.getCategories(bakeryId, { locale })
    .then((categories: FilterCategory[]) => {
      categories = this._transformCategories(categories);
      this.categories = categories;
      return categories;
    });
}

////////////////////////////////// 8 //////////////////////////////////
/**
 * Предупреждение
 * */

export interface Bakery {
  // ...
  // Не использовать это поле, в новых версиях api его не будет
  invoiceWithoutApprove?: boolean;
  // ...
}

////////////////////////////////// 9 //////////////////////////////////
/**
 *
 * */
      /**
       * Don't merge in one
       * size = {
       *      (...parentType && this._extendSizeWithParentTypeSize(size, parentType.sizes)),
       *      ...this._extendSizeWithDiscount(size, discount)
       * }
       * because it is reset product price
       * */
      size = parentType && this._extendSizeWithParentTypeSize(size, parentType.sizes) || size;
      size = { ...this._extendSizeWithDiscount(size, discount) };

////////////////////////////////// 10 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 11 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 12 //////////////////////////////////
/**
 *
 * */





