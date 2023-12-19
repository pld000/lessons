////////////////////////////////// 1 //////////////////////////////////
/**
 *
 * */

async function _initOverview(): Promise<void> {
  this.unpublishedQuantity = null;
  this.products = null;

  const { results: productTypes }: PaginatedResponse<ProductTypeOverview> = await this._getProductTypes();

  // If there are no published products, unpublished count should be checked to show this count on overview page
  if (!productTypes.length && this.published === PUBLISH_STATUSES.PUBLISHED_ONLY) {
    const { count }: PaginatedResponse<ProductTypeOverview> = await this._getProductTypes({ published: 'false' });
    this.unpublishedQuantity = count;
  } else {
    this.products = productTypes;
  }
}

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
 *
 * */


////////////////////////////////// 8 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 9 //////////////////////////////////
/**
 *
 * */


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


////////////////////////////////// 13 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 14 //////////////////////////////////
/**
 *
 * */


////////////////////////////////// 15 //////////////////////////////////
/**
 *
 * */


