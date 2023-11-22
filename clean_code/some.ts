// 6.1
function getCartTotalPrice(cartLines: { [key: number]: ICartLine }): number {
  let cartTotalPrice = 0;

  cartLines.forEach((cartLine) => {
     cartTotalPrice += cartLine.count ? cartLine.price * cartLine.count : 0;
  });

  // ...
  return cartTotalPrice;
}

function mapDefaultValuesToAddOns = (defaultValues: IDefaultValues, addOns: IAddOn[]): IAddOn[] => {
  // ...
  const checkedAddOns = addOns.filter(({checked}) => checked)
  // ...
};


