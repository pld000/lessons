////////////////////////////////// 1 //////////////////////////////////
//Before
function getTimeIntervals(step: number = 1, startRange: number = 0, endRange: number = 24): ITimeInterval[] {
  // короткое, неинформативное имя
  const result = [];
  for (let i = startRange; i < endRange; i += step) {
    // короткое, неинформативное имя
    const start = moment(i, 'HH:mm').format('HH:mm');
    // короткое, неинформативное имя
    const end = moment(i + step, 'HH:mm').format('HH:mm');
    result.push({
      timeRange: `${start}-${end}`,
      price: 0
    });
  }
  return result;
}

//After
function getTimeIntervals(step: number = 1, startRange: number = 0, endRange: number = 24): ITimeInterval[] {
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
//Before
function _updateTotalPriceAndQuantity() {
  const discount = this.cake.discount && this.cake.discount.value || null;
  // Спецификаторы (префиксы и постфиксы)
  let totalPrice = 0;
  let totalQuantity = 0;

  if (this.isPiecesWithMultipleVariants) {
    const {
      total: multiVariantsTotal,
      quantity: multiVariantsQuantity
    } = getPiecesMultipleVariantsPriceAndQuantity(this.piecesVariantCartLines, discount);

    totalPrice += multiVariantsTotal;
    totalQuantity += multiVariantsQuantity;
  } else if (this.isConcreteProductType) {
    totalPrice += getCakeCartLineTotalPrice(this.cartLine, discount);
    totalQuantity += this.cartLine.count;
  }

  this.totalPrice = totalPrice;
  this.totalQuantity = totalQuantity;
}

//After
function _updateTotalPriceAndQuantity() {
  const { discount } = this.cake;
  // Спецификаторы (префиксы и постфиксы)
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


////////////////////////////////// 4 //////////////////////////////////
////////////////////////////////// 5 //////////////////////////////////
////////////////////////////////// 6 //////////////////////////////////
////////////////////////////////// 7 //////////////////////////////////
////////////////////////////////// 8 //////////////////////////////////
////////////////////////////////// 9 //////////////////////////////////
////////////////////////////////// 10 //////////////////////////////////
////////////////////////////////// 11 //////////////////////////////////
////////////////////////////////// 12 //////////////////////////////////
