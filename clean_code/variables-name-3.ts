////////////////////////////////// 7.1 //////////////////////////////////
export class PostNumberDeliveryCheckComponent extends CustomComponent {
// ...
  // differentCityFlag - isDifferentCity
  public isDifferentCity: boolean = true;

  // readyFlag - isReady
  public isReady: boolean = false;
}

export class CakeItemComponent implements OnInit, OnChanges {
  // touchDevice - isTouchDevice
  @Input() isTouchDevice: boolean;
  // ...
}

export class DeliveryToDoorComponent implements OnInit, ComponentSubscriber, HasInitialized, OnDestroy {
  // deliveryAvailable - isDeliveryAvailable
  public isDeliveryAvailable = true;
  // ...
}

export class EmptyPageComponent extends CustomComponent implements OnInit {
  // requestExpired - isRequestExpired
  public isRequestExpired: boolean;
  // ...
}

////////////////////////////////// 7.2 //////////////////////////////////
const onOutletChange = async (info: { outletId: number, date: Date, timeRange: string }) => {
  const { success } = await this.updateOutlet(info);
  if (success) {
    this._analytic.trackCheckoutAction(CHECKOUT_STEPS.FILL_DELIVERY, null, UI_DELIVERY_OPTIONS.PICKUP);
  }
};

async function initPage() {
  //...
  try {
    //...
    this.done = true;
  } catch (e) {
    this.done = false;
  }
}

async function getPayments(orderId: number): IPayment[] {
  try {
    // ...
  } catch (e) {
    this.error = true;
  }
}

////////////////////////////////// 7.3 //////////////////////////////////
for (const orderLineInd = 0; orderLineInd < orderLines.length; orderLineInd++) {
  // ...
  for (const addOnInd = 0; addOnInd < orderLines.addOns.length; addOnInd++) {
    // ...
  }
  // ...
}

////////////////////////////////// 7.4 //////////////////////////////////
class DateRange {
  public begin: Date;
  public end: Date;
}

const getTableOrder = () => {
  // ...
  return `
   <tr class="info-table-row info-table-row-info font-md"
                  [class.last-row]="last"
                  [class.first-row]="first"
                  *ngFor="let analyticData of analyticsData; let last = last; let firs = first">`;
  // ...
};

////////////////////////////////// 7.5 //////////////////////////////////
function palindromeCheck(str: string): boolean { // Before
  const deque = _mapStringToDeque(str);
  const iterations = deque.size() / 2;

  let isPalindrome = true;

  for (const i = 0; i < iterations; i++) {
    isPalindrome = isPalindrome && deque.removeFront() === deque.removeTail();

    if (!isPalindrome) {
      return false;
    }
  }

  return isPalindrome;
}

function palindromeCheck(str: string): boolean { // After
  const deque = _mapStringToDeque(str);
  const iterations = deque.size() / 2;

  for (const i = 0; i < iterations; i++) {
    if (deque.removeFront() != deque.removeTail()) {
      return false;
    }
  }

  return true;
}

// Before
const _saveClosedDay = (d: IProductTypeUnavailableDay): Promise<any> => {
  if (!d.begin || !d.end) {
    return;
  }

  const payload = {
    begin: baDateHelper.fromDateToStr(d.begin as Date),
    end: baDateHelper.fromDateToStr(d.end as Date),
  };

  // ...
};

// After
const _saveClosedDay = (day: IProductTypeUnavailableDay): Promise<any> => {
  if (!day.begin || !day.end) {
    return;
  }

  const payload = {
    begin: baDateHelper.fromDateToStr(day.begin as Date),
    end: baDateHelper.fromDateToStr(day.end as Date),
  };

  // ...
};
