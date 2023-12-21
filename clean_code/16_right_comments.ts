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
  protected _transformProductType(productType: IProductTypeOverview) {
    const transformedProduct = [];
    const productTypeClone = cloneDeep(productType);
    const { sizes, discount, parentType } = productTypeClone;

    sizes.forEach((size: IProductSize, index) => {
      /**
       * A product with standard variant, doesn't have price and productNumber,
       * so there should be set from size;
       * */
      if (this._isStandardVariant(size.products)) {
        const { products, price, productNumber } = size;
        size = { ...size, products: products.map(p => ({ ...p, price, productNumber }))
        };
      }
      }

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





