export class Marker {
  private lat = 0;
  private lng = 0;
  private label: string;
  private draggable: boolean;

  public setLat(lat: number) {
    this.lat = lat;
  }

  public getLat(): number {
    return this.lat;
  }

  public setLng(lng: number) {
    this.lng = lng;
  }

  public getLng(): number {
    return this.lng;
  }

  public setLabel(label: string) {
    this.label = label;
  }

  public getLabel(): string {
    return this.label;
  }

  public setDraggable(draggable: boolean) {
    this.draggable = draggable;
  }

  public getDraggable(): boolean {
    return this.draggable;
  }
}
