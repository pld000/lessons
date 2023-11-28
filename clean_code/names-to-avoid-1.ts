////////////////////////////////// 1 //////////////////////////////////
function getTimeIntervals(step: number = 1, startRange: number = 0, endRange: number = 24): ITimeInterval[] {
  // короткое, неинформативное имя
  //Before
  const result = [];
  //After
  const timeIntervalsGroup = [];

  for (let i = startRange; i < endRange; i += step) {
    const startDate = moment(i, 'HH:mm').format('HH:mm');
    const endDate = moment(i + step, 'HH:mm').format('HH:mm');
    timeIntervalsGroup.push({
      timeRange: `${startDate}-${endDate}`,
      price: 0
    });
  }
  return timeIntervalsGroup;
}

////////////////////////////////// 2 //////////////////////////////////
function _updateTotalPriceAndQuantity() {
  const { discount } = this.cake;
  //Спецификаторы (префиксы и постфиксы)
  //Before
  let totalPrice = 0;
  let totalQuantity = 0;
  //After
  let priceTotal = 0;
  let quantityTotal = 0;

  if (this.isPiecesWithMultipleVariants) {
    const {
      total: multiVariantsTotal,
      quantity: multiVariantsQuantity
    } = getPiecesMultipleVariantsPriceAndQuantity(this.piecesVariantCartLines, discount);

    priceTotal += multiVariantsTotal;
    quantityTotal += multiVariantsQuantity;
  } else if (this.isConcreteProductType) {
    priceTotal += getCakeCartLineTotalPrice(this.cartLine, discount);
    quantityTotal += this.cartLine.count;
  }

  this.priceTotal = priceTotal;
  this.quantityTotal = quantityTotal;
}

////////////////////////////////// 3 //////////////////////////////////
function mapUserInfoToView(user: IUser) {
  //Неинформативные имена
  //Before
  const nameStr = user.firstName + ' ' + user.lastName;
  const addressStr = user.postNumber + ', ' + user.city + ', ' + user.street;
  //After
  const userFullName = user.firstName + ' ' + user.lastName;
  const userFullAddress = user.postNumber + ', ' + user.city + ', ' + user.street;

  return `
  <div class="user-name">{{ userFullName }}</div>
  <div class="user-address">{{ userFullAddress }}</div>
  `;
}

////////////////////////////////// 4 //////////////////////////////////
function sortExtraProducts(productType: IProductType): IExtraProduct[] {
  //Похожие имена, но имеющие разный смысл
  //Before
  const productTypeExtraProductsWithAddons = productType.extraProducts.filter(({ addOns }) => addOns.length > 0);
  const productTypeExtraProductsWithoutAddons = productType.extraProducts.filter(({ addOns }) => addOns.length === 0);
  //After
  const epWithAddons = productType.extraProducts.filter(({ addOns }) => addOns.length > 0);
  const epAddonsEmpty = productType.extraProducts.filter(({ addOns }) => addOns.length === 0);

  return epWithAddons.concat(epAddonsEmpty);
}

////////////////////////////////// 5 //////////////////////////////////
function prepareSearchCriteriaForRequest(criteria: IBakeriesSearchCriteria, areas?: IArea[]) {
  const area = areas ? areas.find(a => a.country === criteria.countryCode) : null;
  // короткое, неинформативное имя
  //Before
  let result = {
    query: criteria.query,
    area: area && area.id || null,
  } as any;
  //After
  let requestCriteria = {
    query: criteria.query,
    area: area && area.id || null,
  } as any;

  if (criteria.statusInclude) {
    requestCriteria = { ...result, statusInclude: criteria.statusInclude };
  }

  if (criteria.sortByLastAction) {
    requestCriteria = { ...result, sortByLastAction: 'true' };
  }

  return requestCriteria;
}

////////////////////////////////// 6 //////////////////////////////////
function getValidDateInterval(dateIntervals: IDateInterval[], currentDateInterval: IDateInterval) {
  // короткое, неинформативное имя
  //Before
  const max = getMaxDateInterval(dateIntervals);
  // After
  const dateIntervalMax = getMaxDateInterval(dateIntervals);

  if (isDateIntervalExpired(dateIntervalMax)) {
    return currentDateInterval;
  }

  return getMaxDateInterval([dateIntervalMax, currentDateInterval]);
}

////////////////////////////////// 7 //////////////////////////////////
//имена со скрытым смыслом
//Before
const hamstadBakeryId = 35;
//After
const privilegesBakeryId = 35;

function initBakery() {
  // ...
  if (this.bakery.id === privilegesBakeryId) {
    applyBakeryPrivileges(this.bakery);
  }
  // ...
}

////////////////////////////////// 8 //////////////////////////////////
async function editOrderLine(orderLine: IOrderLine) {
  try {
    // короткое, неинформативное имя
    //Before
    const result = orderLine.isCustom
      ? await this.showCustomProductEditForm(orderLine)
      : await this.showProductEditForm(orderLine);
    //After
    const updatedOrderLine = orderLine.isCustom
      ? await this.showCustomProductEditForm(orderLine)
      : await this.showProductEditForm(orderLine);

    updatedOrderLine.id
      ? this.editedOrder.updateOrderLine(updatedOrderLine)
      : this.editedOrder.updateOrderLineNew(orderLine, updatedOrderLine);
  } catch (e) {
    console.log('EDIT_ORDER_LINE_ERROR: ', e);
  }
}

////////////////////////////////// 9 //////////////////////////////////
function getExpirationTime(checkingDate: Date): number {
  //...
  //Спецификаторы (суффиксы)
  //Before
  const timeDiff = moment(checkingDate).diff(new Date(), 'hours');
  //After
  const timeDiff_hrs = moment(checkingDate).diff(new Date(), 'hours');
  //...
}

////////////////////////////////// 10 //////////////////////////////////
function extendWithMinPrice(productTypeSizes: ISize[]): ISize[] {
  return productTypeSizes.map((size) => {
    //Спецификаторы (префиксы и постфиксы)
    //Before
    let minPrice = 0;
    //After
    let priceMin = 0;

    if (size.variants.length > 1) {
      const prices = size.variants
        .filter(({ price }) => price > 0)
        .map(({ price }) => price);

      priceMin = Math.min(prices);
    }

    return { ...size, priceMin };
  });
}

////////////////////////////////// 11 //////////////////////////////////
function applyDiscount(price: number, discount: { percentage?: number, value?: number, type: string }): number {
  //Спецификаторы (суффиксы)
  //Before
  const { percentage, value } = discount;
  //After
  const { percentage: discount_pct, value: discount_amt } = discount;

  if (discount_amt) {
    return price - discount_amt;
  }

  if (discount_pct) {
    return price - price / discount_pct * 100;
  }

  return price;
}

////////////////////////////////// 12 //////////////////////////////////
async function initOrders() {
  // Спецификаторы (префиксы и постфиксы)
  //Before
  this.ordersList = await getOrders(this.bakery.id);
  this.totalOrdersSum = calcOrdersSum(this.ordersList);
  //After
  this.ordersGroup = await getOrders(this.bakery.id);
  this.ordersSumTotal = calcOrdersSum(this.ordersList);

  //...
}
