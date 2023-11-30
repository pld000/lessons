// Class names
////////////////////////////////// 1 //////////////////////////////////
// Before
class BuilderManager {
}

// After
class ProductTypeBuilder {
}

////////////////////////////////// 2 //////////////////////////////////
// Before
class OrderPaymentsManager {
}

// After
class OrderPaymentsApi {
}

////////////////////////////////// 3 //////////////////////////////////
// Before
class AnalyticsReportsManager {
}

// After
class AnalyticsReports {
}

////////////////////////////////// 4 //////////////////////////////////
// Before
class WebSocketOrdersService {
}

// After
class OrdersWebSocket {
}

////////////////////////////////// 5 //////////////////////////////////
// Before
class FileUploadService {
}

// After
class FileUploader {
}

// Methods names
class OrdersManager {
  ////////////////////////////////// 1 //////////////////////////////////
  // Before
  public get(orderId: number): Promise<IOrder> {
  }

  // After
  public getOrderById(orderId: number): Promise<IOrder> {
  }

  ////////////////////////////////// 2 //////////////////////////////////
  // Before
  public update(orderId: number, data: IOrder): Promise<IOrder> {
  }

  // After
  public updateOrder(orderId: number): Promise<IOrder> {
  }

  ////////////////////////////////// 3 //////////////////////////////////
  // Before
  public refund(orderId: number): Promise<IOrder> {
  }

  // After
  public makeOrderRefund(orderId: number): Promise<IOrder> {
  }

  ////////////////////////////////// 4 //////////////////////////////////
  // Before
  public getOrders(customerId: number): Promise<IOrder[]> {
  }

  // After
  public getCustomerOrders(customerId: number): Promise<IOrder[]> {
  }
}

class OrdersWebSocket {
  ////////////////////////////////// 5 //////////////////////////////////
  // Before
  public reconnectFor(outletId: number) {
  }

  // After
  public reconnectForOutlet(outletId: number) {
  }

  ////////////////////////////////// 6 //////////////////////////////////
  // Before
  public close(outletId: number) {
  }

  // After
  public stopOutletConnection(outletId: number) {
  }
}

class FileUploader {
  ////////////////////////////////// 7 //////////////////////////////////
  // Before
  public upload(file: File, url: string, params?: any, headers?: { [name: string]: string | string[] }): Promise<any> {
  }

  // After
  public uploadFile(file: File, url: string, params?: any, headers?: { [name: string]: string | string[] }): Promise<any> {
  }
}
