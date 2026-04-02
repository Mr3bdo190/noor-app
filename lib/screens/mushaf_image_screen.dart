import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';

class MushafImageScreen extends StatefulWidget {
  final int startPage;
  const MushafImageScreen({super.key, this.startPage = 1});

  @override
  State<MushafImageScreen> createState() => _MushafImageScreenState();
}

class _MushafImageScreenState extends State<MushafImageScreen> {
  late PageController _pageController;
  int currentPage = 1;

  @override
  void initState() {
    super.initState();
    currentPage = widget.startPage;
    // نعكس الصفحة لأن السحب في العربي من اليمين لليسار
    _pageController = PageController(initialPage: 604 - widget.startPage);
  }

  // رابط صور مصحف المدينة المفتوح المصدر
  String getImageUrl(int page) {
    final pageStr = page.toString().padLeft(3, '0');
    return 'https://raw.githubusercontent.com/quran/quran.com-images/master/width_1024/page$pageStr.png';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF4F4F9), // لون ورق هادي ومريح للعين
      appBar: AppBar(
        backgroundColor: const Color(0xFF0F4C3A), // أخضر إسلامي فخم
        title: Text('صفحة $currentPage', style: const TextStyle(color: Colors.white, fontWeight: FontWeight.bold)),
        centerTitle: true,
        iconTheme: const IconThemeData(color: Colors.white),
      ),
      body: PageView.builder(
        controller: _pageController,
        reverse: true, // عشان يقلب من اليمين للشمال زي المصحف
        itemCount: 604,
        onPageChanged: (index) {
          setState(() {
            currentPage = 604 - index;
          });
        },
        itemBuilder: (context, index) {
          int actualPage = 604 - index;
          return InteractiveViewer( // عشان تقدر تعمل زووم بصوابعك
            child: CachedNetworkImage(
              imageUrl: getImageUrl(actualPage),
              placeholder: (context, url) => const Center(child: CircularProgressIndicator(color: Color(0xFF0F4C3A))),
              errorWidget: (context, url, error) => const Center(child: Icon(Icons.wifi_off, size: 50, color: Colors.grey)),
              fit: BoxFit.contain,
            ),
          );
        },
      ),
    );
  }
}
