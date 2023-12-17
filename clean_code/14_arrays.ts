////////////////////////////////// 1 //////////////////////////////////
/**
 * Последовательный перебор элементов без прямой индексации, вместо
 * массива можно использовать очередь
 * */
const getOrderLinesCount = (lines: OrderLine[]): number => {
  let count = 0;
  for (let i = 0; i < lines.length; i++) {
    count += lines[i].count;
  }
  return count;
};

////////////////////////////////// 2 //////////////////////////////////
/**
 * Несмотря на отсутствие прямой индексации, более эффективно было бы
 * воспользоваться словарем
 * PRODUCT_IMAGE_ASPECT_RATIOS = {
 *   RECT_VERTICAL: 0.652777777777778
 *   RECT_HORIZONTAL: 1.486111111111111
 * }
 * */
export const PRODUCT_IMAGE_ASPECT_RATIOS = [
  { ratio: 0.652777777777778, type: RECT_VERTICAL },
  { ratio: 1.486111111111111, type: RECT_HORIZONTAL },
  // ...
];

const getImageAspectRatio = (type: string): number => {
  for (let i = 0; i < PRODUCT_IMAGE_ASPECT_RATIOS.length; i++) {
    if (type === PRODUCT_IMAGE_ASPECT_RATIOS[i].type) {
      return PRODUCT_IMAGE_ASPECT_RATIOS[i].ratio;
    }
  }

  return null;
};

////////////////////////////////// 3 //////////////////////////////////
/**
 * productNames - массив уникальных имен продуктов, вместо массива удобней
 * и эффективней использовать множества
 * */
function getUnavailableProductNames(shops: WebShop): string[] {
  const productNames: string[] = [];
  for (let i = 0; i < shops.length; i++) {
    const { unavailableProductTypes } = shops[i];
    for (let j = 0; j < unavailableProductTypes.length; j++) {
      const { name } = unavailableProductTypes[j];
      if (productNames.indexOf(name) === -1) {
        productNames.push(name);
      }
    }
  }

  return productNames;
}

////////////////////////////////// 4 //////////////////////////////////
/**
 * Загрузка каждого файла, оборачивается в промис, и кладется в массив
 * промисов. Затем происходит resolve всех промисов одновременно. Такое
 * решение является крайне неверным. Вместо массива promises, лучше всего использовать очередь или
 * стэк, обрабатывая промисы последовательно.
 * */
function uploadFiles($event) {
  const maxCount = this.maxImagesCount - this.attachments.length;

  if (maxCount <= 0) {
    return;
  }
  // ...
  const files = Array.from($event.target.files).slice(0, maxCount);
  $event.target.value = '';

  // ...

  const promises = [];
  for (let i = 0; i < files.length; i++) {
    promises.push(
      new Promise(() => this._imageFileProcess(files[i]))
    );
  }
  return Promise.all(promises);
}

////////////////////////////////// 5 //////////////////////////////////
/**
 * Последовательная обработка двумерного массива без прямого обращения по индексу
 * */
const recalculationOrderLinesSums = (orderLines: OrderLine[]): number => {
  let total = 0;

  orderLines.forEach((orderLine: OrderLine) => {
    if (orderLine.status !== PRODUCT_STATUSES.DELETED && orderLine.type !== ORDER_LINE_TYPES.DISCOUNT) {
      orderLine.orderLines.forEach((subLine: OrderLine) => {
        if (subLine.status !== PRODUCT_STATUSES.DELETED) {
          // ...
          const price = subLine.price || 0;

          subLine.total = price * subLine.quantity;
          // ...
          total += subLine.total;
        }
      });

      // ...
      total += orderLine.price * orderLine.quantity;
    }
  });

  return total;
};


