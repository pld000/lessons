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
  // Спецификаторы (префиксы и постфиксы)
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
  const extraProductsWithAddons = productType.extraProducts.filter(({ addOns }) => addOns.length > 0);
  const extraProductsAddonsEmpty = productType.extraProducts.filter(({ addOns }) => addOns.length === 0);

  return extraProductsWithAddons.concat(extraProductsAddonsEmpty);
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
////////////////////////////////// 8 //////////////////////////////////
////////////////////////////////// 9 //////////////////////////////////
////////////////////////////////// 10 //////////////////////////////////
////////////////////////////////// 11 //////////////////////////////////
////////////////////////////////// 12 //////////////////////////////////
