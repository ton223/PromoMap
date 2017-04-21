import { PromomapWebPage } from './app.po';

describe('promomap-web App', () => {
  let page: PromomapWebPage;

  beforeEach(() => {
    page = new PromomapWebPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
