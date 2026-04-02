import 'package:flutter/material.dart';
import 'screens/khatmah_plan_screen.dart';
import 'screens/quran_screen.dart';
import 'screens/prayer_screen.dart';
import 'screens/adhkar_screen.dart';
import 'screens/qibla_screen.dart';

void main() {
  runApp(const NoorApp());
}

class NoorApp extends StatelessWidget {
  const NoorApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'نور',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        scaffoldBackgroundColor: const Color(0xFFF9FAFB), // خلفية بيضاء مريحة
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFF0F4C3A), // أخضر ختمة
          primary: const Color(0xFF0F4C3A),
          secondary: const Color(0xFFD4AF37), // ذهبي
        ),
        appBarTheme: const AppBarTheme(backgroundColor: Color(0xFF0F4C3A), foregroundColor: Colors.white),
      ),
      builder: (context, child) {
        return Directionality(textDirection: TextDirection.rtl, child: child!);
      },
      home: const MainScreen(),
    );
  }
}

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int _currentIndex = 0;

  final List<Widget> _screens = [
    const KhatmahPlanScreen(), // خلينا شاشة الختمة هي الرئيسية
    const QuranScreen(), // فهرس السور القديم
    const PrayerScreen(),
    const AdhkarScreen(),
    const QiblaScreen(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _screens[_currentIndex],
      bottomNavigationBar: NavigationBar(
        selectedIndex: _currentIndex,
        onDestinationSelected: (index) {
          setState(() {
            _currentIndex = index;
          });
        },
        backgroundColor: Colors.white,
        indicatorColor: const Color(0xFF0F4C3A).withOpacity(0.15),
        destinations: const [
          NavigationDestination(icon: Icon(Icons.menu_book), label: 'ختمتي'),
          NavigationDestination(icon: Icon(Icons.list_alt), label: 'الفهرس'),
          NavigationDestination(icon: Icon(Icons.access_time), label: 'الصلاة'),
          NavigationDestination(icon: Icon(Icons.format_list_bulleted), label: 'الأذكار'),
          NavigationDestination(icon: Icon(Icons.explore), label: 'القبلة'),
        ],
      ),
    );
  }
}
