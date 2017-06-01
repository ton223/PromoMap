export class Graph {

  private origin: any;
  private vertexs: any[];
  private arris: any[];

  public getOrigin(): any {
    return this.origin;
  }

  public setOrigin(origin: any): void {
    this.origin = origin;
  }

  public listAllProducts() {
    let products = [];
    for(let i = 0; i < this.vertexs.length; i++) {
      let vertex = this.vertexs[i];
      if(vertex.type == 'COMPANY'){
          for(let i = 0; i < vertex.products.length; i++) {
            products.push(vertex.products[i]);
          }
      }
    }
    return products;
  }

  public filterByCategorysAndDistance(filter: any[], distance: number): any[] {
    let products = [];
    let categoryVertexs = this.listAdjacents(this.origin);
    for (let j = 0; j < filter.length; j++) {
      if(filter[j].show == true) {
        for (let i = 0; i < categoryVertexs.length; i++) {
          // console.log(filter[j].category+' == '+categoryVertexs[i].category)
          if(filter[j].category == categoryVertexs[i].category) {
            let companyVertexs = this.listAdjacentsInradius(categoryVertexs[i], distance);
            for(let a = 0; a < companyVertexs.length; a++) {
              let vertex = companyVertexs[a];
                for(let b = 0; b < vertex.products.length; b++) {
                  products.push(vertex.products[b]);
                }
            }
          }
        }
      }
    }
    return products;
  }

  private listAdjacents(v: any) {
		let adjacentes = [];
		for (let i = 0; i < this.arris.length; i++) {
      let a = this.arris[i];
			if (this.equals(a.vertexA, v)) {
				adjacentes.push(a.vertexB);
			} else if (this.equals(a.vertexB, v)) {
				adjacentes.push(a.vertexA);
			}
		}
		return adjacentes;
	}

  private listAdjacentsInradius(v: any, distance: number) {
		let adjacentes = [];
		for (let i = 0; i < this.arris.length; i++) {
      let a = this.arris[i];
			if (this.equals(a.vertexA, v)) {
        if(this.getDistance(v, a.vertexB) < distance) {
				  adjacentes.push(a.vertexB);
        }
			}
		}
		return adjacentes;
	}

  private getDistance(v1: any, v2: any): number {
		for (let i = 0; i < this.arris.length; i++) {
      let a = this.arris[i];
			if (this.equals(a.vertexA,v1) && this.equals(a.vertexB, v2) || this.equals(a.vertexA, v2) && this.equals(a.vertexB, v1)) {
				return a.distance;
			}
		}
		return -1;
	}

  private equals(v1: any, v2: any): boolean {
    if(v1.id == v2.id) {
      return true;
    } else {
      return false;
    }
  }
}
