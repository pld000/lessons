////////////////////////////////// 6.1 //////////////////////////////////
const applyDiscount = (cartLine: ICartLine): void => {
  const { price, discount } = cartLine;
  const discountedPrice = price - price * discount / 100;
  // ...
};

const getCartTotalPrice = (cartLines: ICartLine[]): number => {
  // ...
  let cartTotalPrice = 0;
  // ...
  cartLines.forEach(({ count, price }: ICartLine) => {
    cartTotalPrice += count ? price * count : 0;
  });
  return cartTotalPrice;
};

const mapDefaultValuesToAddOns = (defaultValues: IDefaultValues, addOns: IAddOn[]): IAddOn[] => {
  // ...
  const checkedAddOns = addOns.filter(({ checked }) => checked);
  // ...
};

const getProductPrintingPicture = (product: IProduct): IPrintingPicture => {
  const { size } = product;

  // ...
  const productPrintingPicture = size.addOns.find(({ type }: IAddOn) => type === ADDONS_TYPES.PRINTING_PICTURE);
  // ...
};

const mapArrayToDictionaryByItemId = (inputArray: any[]): { [key: number]: any } => {
  const dictionaryByItemId = {};

  for (const i = 0; i < inputArray.length; i++) {
    dictionaryByItemId[i] = inputArray[i];
  }

  return dictionaryByItemId;
};


////////////////////////////////// 6.2 //////////////////////////////////
class Stack {
}

class Deque<T> {
  addFront: any;
}

class BloomFilter {
  isValue;

  constructor(s) {
  }
}

class Queue<T> {
  enqueue;
}

const checkBalance = (brackets: String): boolean => {
  const stack = new Stack();
  const resultStack = new Stack();
  // ...
};

const _mapStringToDeque = (str: String): Deque<String> => {
  const deque = new Deque();
  str = str.toLowerCase();
  for (const i = 0; i < str.length; i++) {
    deque.addFront(str.charAt(i));
  }
  return deque;
};

const canBeAddedToSet = (value: string, setOfStrings: String[]): boolean => {
  const bloomFilter = new BloomFilter(setOfStrings);
  return bloomFilter.isValue(value);
};

const makeUploadingProductQueue = (uploadingProducts: Promise<IProduct>[]): Queue<IProduct> => {
  const queue = new Queue();

  for (const i = 0; i < uploadingProducts.length; i++) {
    queue.enqueue(uploadingProducts[i]);
  }

  return queue;
};


////////////////////////////////// 6.3 //////////////////////////////////
const getInvoiceContactInfo = (customerContacts: ICustomerContactInfo, companyContacts: ICompanyContactInfo): IInvoiceContactInfo => {
  const { address: customerAddress, phone: customerPhone, email: customerEmail } = customerContacts;
  const { address: companyAddress, phone: companyPhone, email: companyEmail } = companyContacts;

  return { customerAddress, customerPhone, customerEmail, companyAddress, companyPhone, companyEmail };
};

const updateProduct = async (product: IProduct, data: any): IProduct => {
  const defaultSize = getDefaultSize();
  const productSize = getProductSize(product.id);
  // ...
  product.size = { ...defaultSize, ...productSize };
};

class OrderDelivery {
  constructor(private _userState: State<IUser>,
              private _cartState: State<ICart>) {
  }
}


////////////////////////////////// 6.4 //////////////////////////////////
// productUnavailableDay = unavailableDay
// allowToConnectAddonsToProduct = ableToConnectAddons
// repeatAddonsOverSizes = repeatOverSizes
// showConnectingVariantSection = showVariantConnect
// productTypeListPosition = productPosition