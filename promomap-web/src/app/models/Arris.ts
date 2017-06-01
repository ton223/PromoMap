import { Vertex } from './Vertex';

export class Arris {

  private distance: number;
  private vertexA: Vertex;
  private vertexB: Vertex;


  public getDistance(): number {
    return this.distance;
  }

  public setDistance(distance: number): void {
    this.distance = distance;
  }

  public getVertexA(): Vertex {
    return this.vertexA;
  }

  public setVertexA(vertexA: Vertex): void {
    this.vertexA = vertexA;
  }

 public getVertexB(): Vertex {
    return this.vertexB;
  }

  public setVertexB(vertexB: Vertex): void {
    this.vertexB = vertexB;
  }
}
