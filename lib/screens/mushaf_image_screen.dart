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
    _pageController = PageController(initialPage: 604 - widget.startPage);
  }

  String getImageUrl(int page) {
    final pageStr = page.toString().padLeft(3, '0');
    return 'https://raw.githubusercontent.com/quran/quran.com-images/master/width_1024/page$pageStr.png';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF9FAFB),
      appBar: AppBar(
        backgroundColor: const Color(0xFF0F4C3A),
        title: Text('صفحة $currentPage', style: const TextStyle(color: Colors.white, fontWeight: FontWeight.bold)),
        centerTitle: true,
        iconTheme: const IconThemeData(color: Colors.white),
      ),
      body: PageView.builder(
        controller: _pageController,
        reverse: true,
        itemCount: 604,
        onPageChanged: (index) {
          setState(() {
            currentPage = 604 - index;
          });
        },
        itemBuilder: (context, index) {
          int actualPage = 604 - index;
          return InteractiveViewer(
            child: CachedNetworkImage(
              imageUrl: getImageUrl(actualPage),
              placeholder: (context, url) => const Center(child: CircularProgressIndicator(color: Color(0xFFD4AF37))),
              errorWidget: (context, url, error) => const Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Icon(Icons.wifi_off, size: 60, color: Colors.grey),
                  SizedBox(height: 15),
                  Text('تأكد من الاتصال بالإنترنت لتحميل الصفحة', style: TextStyle(color: Colors.grey, fontSize: 18)),
                ],
              ),
              fit: BoxFit.contain,
            ),
          );
        },
      ),
    );
  }
}
